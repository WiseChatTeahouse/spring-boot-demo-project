package chat.wisechat.mqtt;

import chat.wisechat.mqtt.consumer.MqttCallback;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.mqtt
 * @Date 2024/9/18 9:48
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(MqttProperties.class)
public class MqttAutoConfiguration {
    @Resource
    private MqttProperties mqttProperties;
    @Resource
    private MqttCallback mqttCallback;


    @Bean
    public MqttClient mqttClient() {
        try {
            MqttClient mqttClient = new MqttClient(mqttProperties.getBroker(), mqttProperties.getClientId(), mqttClientPersistence());
            mqttClient.setManualAcks(true); //设置手动消息接收确认
            mqttCallback.setMqttClient(mqttClient);
            mqttClient.setCallback(mqttCallback);
            mqttClient.connect(mqttConnectOptions());
            return mqttClient;
        } catch (MqttException e) {
            log.error("MQTT client init fail! {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        if (StringUtils.hasLength(mqttProperties.getUsername())) {
            options.setUserName(mqttProperties.getUsername());
        }
        if (StringUtils.hasLength(mqttProperties.getPassword())) {
            options.setPassword(mqttProperties.getPassword().toCharArray());
        }
        options.setAutomaticReconnect(true);//是否自动重新连接
        options.setCleanSession(true);//是否清除之前的连接信息
        options.setConnectionTimeout(mqttProperties.getConnectionTimeout());//连接超时时间
        options.setKeepAliveInterval(mqttProperties.getKeepAliveInterval());//心跳
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);//设置mqtt版本
        return options;
    }

    public MqttClientPersistence mqttClientPersistence() {
        return new MemoryPersistence();
    }
}
