package chat.wisechat.message;

import chat.wisechat.message.core.MessageTemplate;
import chat.wisechat.message.core.OkHttpHelper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.message
 * @Date 2025/3/3 20:18
 */
@Slf4j
@AutoConfiguration
@Import({MessageTemplate.class, OkHttpHelper.class})
@EnableConfigurationProperties(MessageCenterProperties.class)
public class MessageCenterAutoConfiguration {

    private static final TimeUnit SECONDS = TimeUnit.SECONDS;

    @Resource
    private MessageCenterProperties messageCenterProperties;

    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(messageCenterProperties.getOkHttpProperties().getConnectTimeout(), SECONDS);
        builder.readTimeout(messageCenterProperties.getOkHttpProperties().getReadTimeout(), SECONDS);
        builder.writeTimeout(messageCenterProperties.getOkHttpProperties().getWriteTimeout(), SECONDS);
        builder.connectionPool(new ConnectionPool(messageCenterProperties.getOkHttpProperties().getConnectionPool_maxIdleConnections(),
                messageCenterProperties.getOkHttpProperties().getConnectionPool_keepAliveDuration(), SECONDS));
        return builder.build();
    }

}
