package chat.wisechat.message;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.message
 * @Date 2025/3/15 10:07
 */
@Data
@ConfigurationProperties(prefix = "wisechat.message.center")
public class MessageCenterProperties {

    /**
     * 飞书连接前缀
     */
    private String feishuUrl;
    /**
     * 企业微信连接前缀
     */
    private String weixinUrl;
    /**
     * okhttp 连接配置
     */
    private OkHttpProperties okHttpProperties = new OkHttpProperties();

    @Data
    public static class OkHttpProperties {

        /**
         * 表示与目标服务器建立 TCP 连接的最大等待时间（包含 TCP 握手 + SSL 握手时间） 单位：s
         */
        private int connectTimeout = 10;
        /**
         * 表示从成功建立连接后，等待服务器返回响应数据的最大时间  单位：s
         */
        private int readTimeout = 5;
        /**
         * 示向服务器发送请求数据的最大时间(如上传文件)  单位：s
         */
        private int writeTimeout = 5;
        /**
         * 连接池 最大空闲连接数  单位：s
         */
        private int connectionPool_keepAliveDuration = 10;
        /**
         * 连接池 空闲连接的存活时间 默认十分钟  单位：s
         */
        private int connectionPool_maxIdleConnections = 600;
    }

}
