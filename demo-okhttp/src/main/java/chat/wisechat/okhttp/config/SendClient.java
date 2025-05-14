package chat.wisechat.okhttp.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

/**
 * @Author Siberia.Hu
 * @Date 2025/5/12 18:04
 */
@Slf4j
@Component
public class SendClient {

    private static OkHttpClient okHttpClient;

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @PostConstruct
    private void init() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(new SendReqInterceptor());
        okHttpClient = okHttpBuilder.build();
    }

    /**
     * 发送post请求 支持携带请求头
     *
     * @param url    请求路径
     * @param header 请求头
     * @param body   请求体
     * @return 响应结果
     */
    public String doPost(String url, Map<String, String> header, String body) {
        RequestBody requestBody = RequestBody.create(JSON, body);
        Request.Builder request = new Request.Builder();
        request.url(url);
        request.post(requestBody);
        if (!CollectionUtils.isEmpty(header)) {
            for (String key : header.keySet()) {
                request.addHeader(key, header.get(key));
            }
        }
        request.addHeader(SendReqInterceptor.SOURCE_NAME, base64Encode("hello world"));
        return invoke(request.build());
    }

    private String base64Encode(String body) {
        byte[] encode = Base64.getEncoder().encode(body.getBytes(StandardCharsets.UTF_8));
        return new String(encode, StandardCharsets.UTF_8);
    }

    private String invoke(Request request) {
        try (Response response = okHttpClient.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (body!= null) {
                return body.string();
            }
        } catch (IOException e) {
            log.error("CouponHttpClient Invoke Exception", e);
        }
        return "";
    }

}
