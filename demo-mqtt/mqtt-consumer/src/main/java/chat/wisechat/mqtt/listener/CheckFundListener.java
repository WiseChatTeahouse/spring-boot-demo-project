package chat.wisechat.mqtt.listener;

import chat.wisechat.mqtt.annotation.MqttListener;
import chat.wisechat.mqtt.business.MessageHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.mqtt.listener
 * @Date 2024/9/18 10:14
 */
@Slf4j
@MqttListener(topic = "mqtt/topic01")
public class CheckFundListener implements MessageHandler {

    /**
     * 校验资金处理器
     *
     * @param jsonMsg 消息报文
     * @throws IOException 异常
     */
    @Override
    public void execute(String jsonMsg) throws IOException {
        log.info("CheckFundListener execute {}", jsonMsg);
    }
}
