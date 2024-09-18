package chat.wisechat.mqtt;

import chat.wisechat.mqtt.producer.MqttTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.mqtt
 * @Date 2024/9/18 10:27
 */
@SpringBootTest
public class ProducerTest {

    @Resource
    private MqttTemplate mqttTemplate;


    @Test
    public void sendMsg() {
        mqttTemplate.send("hello mqtt!");
    }

}
