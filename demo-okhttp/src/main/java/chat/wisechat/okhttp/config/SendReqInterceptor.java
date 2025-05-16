package chat.wisechat.okhttp.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Author Siberia.Hu
 * @Date 2025/5/12 18:06
 */
@Slf4j
public class SendReqInterceptor implements Interceptor {

    public static final String SOURCE_NAME = "X-Source-Name";

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        String header = request.header(SOURCE_NAME);
        String url = request.url().toString();
        String couponName = base64Decode(header);
        RequestBody body = request.body();
        if (body != null) {
            try (Buffer buffer = new Buffer();) {
                body.writeTo(buffer);
                log.info("Coupon req info url:{} couponName:{} body:{}", url, couponName, buffer.readUtf8());
            } catch (Exception e) {
                log.warn("异常", e);
            }
        }
        try (Response response = chain.proceed(request)) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String result = responseBody.string();
                log.info("Coupon resp info couponName:{} body:{}", couponName, result);
                ResponseBody newResponseBody = ResponseBody.create(result, MediaType.parse("application/json; charset=utf-8"));
                return response.newBuilder().body(newResponseBody).build();
            }
        } catch (IOException e) {
            log.error("Coupon intercept error!", e);
            throw e;
        }
        log.warn("[intercept] 领取券异常 url:{} couponName:{}", url, couponName);
        return null;
    }

    private String base64Decode(String body) {
        if (!StringUtils.hasLength(body)) {
            return "券名称有误";
        }
        byte[] decode = Base64.getDecoder().decode(body.getBytes(StandardCharsets.UTF_8));
        return new String(decode, StandardCharsets.UTF_8);
    }
}
