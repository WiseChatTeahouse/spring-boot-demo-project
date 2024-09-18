package chat.wisechat.mqtt.consumer;

import chat.wisechat.mqtt.business.MessageHandler;
import chat.wisechat.mqtt.business.MessageHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.mqtt.consumer
 * @Date 2024/9/18 10:00
 */
@Slf4j
@Component
public class MqttServiceImpl implements MqttService {
    @Resource
    private MessageHandlerContext messageHandlerContext;

    @Override
    public void dispatchMessage(String topic, MqttMessage message) {
        // 处理消息
        String msgContent = new String(message.getPayload());
        try {
            MessageHandler msgHandler = messageHandlerContext.getMsgHandler(topic);
            if (null == msgHandler) {
                log.warn("MQTT server no topic info topic: {}", topic);
                return;
            }
            log.info("MQTT receive messages topic: {} handler: {}", topic, msgHandler.getClass().getName());
            msgHandler.execute(msgContent);
        } catch (IOException e) {
            log.error("MQTT execute msg fail,msg is: {} error reason: {}", msgContent, e.getMessage());
        }
    }
}
