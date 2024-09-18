package chat.wisechat.mqtt.listener;

import chat.wisechat.mqtt.annotation.MqttListener;
import chat.wisechat.mqtt.business.MessageHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.mqtt.listener
 * @Date 2024/9/18 10:13
 */
@Slf4j
@MqttListener(topic = "mqtt/topic02")
public class FundPoolListener implements MessageHandler {

    /**
     * 资金池校验处理器
     *
     * @param jsonMsg 报文
     * @throws IOException 异常
     */
    @Override
    public void execute(String jsonMsg) throws IOException {
        log.info("FundPoolListener execute {}", jsonMsg);
    }
}
