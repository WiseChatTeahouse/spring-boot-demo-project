package chat.wisechat.mqtt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.mqtt
 * @Date 2024/9/18 9:47
 */
@Data
@ConfigurationProperties(prefix = "wisechat.mq.mqtt")
public class MqttProperties {
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * mqtt的broker地址 tcp://127.0.0.1:1883
     */
    private String broker;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 心跳
     */
    private int keepAliveInterval;
    /**
     * 连接超时时间
     */
    private int connectionTimeout;

    private final Consumer consumer = new Consumer();

    private final Producer producer = new Producer();


    @Data
    public static class Consumer {
        /**
         * 监听的topic集合
         */
        private List<String> topics;
    }

    @Data
    public static class Producer {
        /**
         * 默认的topic
         */
        private String topic;
        /**
         * 默认的消息质量
         */
        private int qos;
        /**
         * 保留消息
         */
        private boolean retained;
    }
}
