package chat.wisechat.mqtt.producer;

import chat.wisechat.mqtt.MqttProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.mqtt.producer
 * @Date 2024/9/18 9:52
 */
@Slf4j
@Component
public class MqttTemplate {

    @Resource
    private MqttClient mqttClient;
    @Resource
    private MqttProperties mqttProperties;

    /**
     * 发送消息到默认的topic
     *
     * @param payload 消息
     */
    public void send(String payload) {
        this.send(mqttProperties.getProducer().getTopic(), payload);
    }

    /**
     * 发送消息到指定的topic
     *
     * @param topic   主题
     * @param payload 消息
     */
    public void send(String topic, String payload) {
        this.send(topic, mqttProperties.getProducer().getQos(), payload);
    }

    /**
     * 发送消息到指定qos的topic
     *
     * @param topic   主题
     * @param qos     消息质量
     * @param payload 消息
     */
    public void send(String topic, int qos, String payload) {
        this.send(topic, qos, mqttProperties.getProducer().isRetained(), payload);
    }

    /**
     * 发送消息到指定qos的topic和是否保留消息
     *
     * @param topic    消息主题
     * @param qos      消息质量
     * @param retained 保留消息
     * @param payload  消息
     */
    public void send(String topic, int qos, boolean retained, String payload) {
        try {
            mqttClient.publish(topic, payload.getBytes(), qos, retained);
        } catch (MqttException e) {
            log.error("publish msg error. {}", e.getMessage());
        }
    }

    /**
     * 发送延迟消息
     */
    public void sendLazyMessage() {

    }

}
