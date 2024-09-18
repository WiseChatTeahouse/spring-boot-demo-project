package chat.wisechat.mqtt.business;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.mqtt.business
 * @Date 2024/9/18 10:01
 */
public interface MessageHandlerContext {
    MessageHandler getMsgHandler(String msgType);
}
