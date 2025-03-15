package chat.wisechat.client.config;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author Siberia.Hu
 * @Date 2025/3/3 15:31
 */
@Slf4j
@Configuration
@EnableWebSocket
public class WsClientConfig {

    @Bean
    public WebSocketClient webSocketClient() {
        WebSocketClient wsc = null;

        try {
            wsc = new WebSocketClient(new URI("ws://localhost:3690/websocket-server")) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    log.info("与服务端建立连接");
                }

                @Override
                public void onMessage(String s) {
                    log.info("收到服务端的消息：{}", s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    log.info("与服务端的连接断开 code:{} reason:{} {}", i, s, b);
                }

                @Override
                public void onError(Exception e) {
                    log.info("连接报错");
                }
            };
            wsc.connect();
        } catch (URISyntaxException e) {
            log.error("初始化客户端异常......", e);
        }

        return wsc;
    }
}
