package chat.wisechat.mqtt.consumer;

import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.mqtt.consumer
 * @Date 2024/9/18 10:00
 */
public interface MqttService {
    /**
     * 分发消息 （策略模式）
     *
     * @param topic   主题
     * @param message 消息
     */
    void dispatchMessage(String topic, MqttMessage message);
}
