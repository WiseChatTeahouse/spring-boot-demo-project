package chat.wisechat.mqtt.business;

import java.io.IOException;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.mqtt.business
 * @Date 2024/9/18 10:01
 */
public interface MessageHandler {
    void execute(String jsonMsg) throws IOException;
}
