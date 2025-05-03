package chat.wisechat.socket.consumer;

import chat.wisechat.socket.ws.WebSocketServer;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.socket.consumer
 * @Date 2025/5/3 20:56
 */
@Slf4j
@Component
@RabbitListener(queues = "#{dynamicQueue.name}")
public class WsListener {

    @Value("${spring.application.instance-id:default}")
    private String instanceId;

    @RabbitHandler
    public void process(String message) {
        log.info("实例：{} 消费消息：{}", instanceId, message);
        JSONObject jsonObject = JSON.parseObject(message);
        // 获取websocket实例
        CopyOnWriteArraySet<WebSocketServer> webSocketSet = WebSocketServer.getWebSocketSet();
        for (WebSocketServer item : webSocketSet) {
            if (Objects.equals(item.getSid(), jsonObject.get("id"))) {
                try {
                    log.info("用户连接存在 {}", String.valueOf(jsonObject.get("id")));
                    item.sendMessage(String.valueOf(jsonObject.get("msg")));
                } catch (IOException e) {
                    log.error("消息发送失败！", e);
                }
            }
        }
    }

}
