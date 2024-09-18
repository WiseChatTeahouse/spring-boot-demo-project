package chat.wisechat.mqtt.service.impl;

import chat.wisechat.mqtt.producer.MqttTemplate;
import chat.wisechat.mqtt.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.mqtt.service.impl
 * @Date 2024/9/18 10:49
 */
@Slf4j
@Service
public class ProducerServiceImpl implements ProducerService {

    @Resource
    private MqttTemplate mqttTemplate;

    @Override
    public String sendMsg(String msg) {
        log.info("ProducerServiceImpl sendMsg {}", msg);
        mqttTemplate.send(msg);
        return msg;
    }
}
