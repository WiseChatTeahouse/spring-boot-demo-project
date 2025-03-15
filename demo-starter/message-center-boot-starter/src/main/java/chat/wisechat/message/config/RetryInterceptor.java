package chat.wisechat.message.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.message.config
 * @Date 2025/3/15 21:32
 */
@Slf4j
public class RetryInterceptor implements Interceptor {

    private static final int MAX_RETRY_COUNT = 3;
    private static final int RETRY_TIME = 3;
    private int retryCount = 1;

    private int waitTime = 1;

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        log.info("来到了重试拦截器");
        Response response = chain.proceed(chain.request());

        while (!response.isSuccessful() && retryCount <= MAX_RETRY_COUNT) {
            log.debug("RetryInterceptor intercept 请求失败，将在{}秒后重试（第{}次）", waitTime, retryCount);
            try {
                TimeUnit.SECONDS.sleep(waitTime);
                response.close();
                response = chain.proceed(chain.request());
                retryCount++;
                waitTime *= RETRY_TIME;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("重试被中断", e);
            }
        }
        // TODO:根据状态码判断重试
        if (!response.isSuccessful()) {
            log.error("RetryInterceptor intercept 达到重试次数，请检查服务是否正常。url: {} request: {}", chain.request()
                    .url(), chain.request().body());
        }
        return response;
    }
}
