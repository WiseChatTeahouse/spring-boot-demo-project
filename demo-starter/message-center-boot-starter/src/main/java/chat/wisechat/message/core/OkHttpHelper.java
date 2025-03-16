package chat.wisechat.message.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.messaging.Message;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.message.core
 * @Date 2025/3/15 14:50
 */
@Slf4j
public class OkHttpHelper {

    public static final String MEDIA_TYPE_JSON = "application/json; charset=utf-8";

    @Resource
    private OkHttpClient okHttpClient;

    /**
     * 发送post的okhttp请求
     *
     * @param url     请求路径
     * @param message 消息报文
     * @return 响应结果
     */
    public String post(String url, Message<?> message) {
        log.debug("okHttpClient post url: {}, body: {}", url, message.getPayload());
        Object path = message.getHeaders().get("path");
        if (null != path) {
            url = url.concat(path.toString());
        }
        MediaType mediaTypeJson = MediaType.parse(MEDIA_TYPE_JSON);
        Headers.Builder builder = new Headers.Builder();
        Headers headers = builder.add("Content-Type", "application/json").build();
        RequestBody requestBody = RequestBody.create(serializableStr(message), mediaTypeJson);
        Request request = new Request.Builder().url(url).headers(headers).post(requestBody).build();
        String result = request(okHttpClient, request);
        log.debug("okHttpClient post url: {}, result: {}", url, result);
        return result;
    }

    public static String request(OkHttpClient okHttpClient, Request request) {
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.body() != null) {
                return response.body().string();
            }
        } catch (IOException e) {
            log.error("OkHttp 请求失败，URL: {}", request.url(), e);
        }
        return null;
    }

    private String serializableStr(Message<?> message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String messageJson = objectMapper.writeValueAsString(message.getPayload());
            log.debug("messageJson = {}", messageJson);
            return messageJson;
        } catch (JsonProcessingException e) {
            log.error("serializableStr 序列化消息对象发生异常 URL: {}", message, e);
            throw new RuntimeException(e);
        }
    }

}
