package chat.wisechat.mqtt.consumer;

import chat.wisechat.mqtt.MqttProperties;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.mqtt.consumer
 * @Date 2024/9/18 9:59
 */
@Slf4j
@Component
public class MqttCallback implements MqttCallbackExtended {
    @Setter
    private MqttClient mqttClient;
    @Resource
    private MqttService mqttService;
    @Resource
    private MqttProperties mqttProperties;

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        MqttProperties.Consumer consumer = mqttProperties.getConsumer();
        List<String> consumerTopics = consumer.getTopics();
        if (null != consumerTopics && !consumerTopics.isEmpty()) {
            consumerTopics.forEach(topic -> {
                try {
                    log.info("MQTT subscribe --->> topic: {}", topic);
                    mqttClient.subscribe(topic, 2);
                } catch (MqttException e) {
                    log.error("MQTT subscribe --->> topic fail!" + e.getMessage());
                }
            });
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        log.error("MQTT connection lost with error: ", cause);
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("MQTT receive messages --->> topic: {} Qos: {} message content: {}", topic, message.getQos(), new String(message.getPayload()));
        // 分发处理消息 (策略模式)
        mqttService.dispatchMessage(topic, message);
        //处理成功后确认消息 ack
        mqttClient.messageArrivedComplete(message.getId(), message.getQos());
        log.trace("MQTT dispose message success!");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
