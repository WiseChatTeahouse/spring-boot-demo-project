package chat.wisechat.mqtt.business.handler;

import chat.wisechat.mqtt.annotation.MqttListener;
import chat.wisechat.mqtt.business.MessageHandler;
import chat.wisechat.mqtt.business.MessageHandlerContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.mqtt.business.handler
 * @Date 2024/9/18 10:02
 */
@Component
public class MessageHandlerContextImp implements ApplicationContextAware, MessageHandlerContext {

    private final Map<String, MessageHandler> handlerMap = new HashMap<>();

    @Override
    public MessageHandler getMsgHandler(String msgType) {
        return handlerMap.get(msgType);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, MessageHandler> map = applicationContext.getBeansOfType(MessageHandler.class);
        map.values().forEach(msgHandler -> {
            String topic = msgHandler.getClass().getAnnotation(MqttListener.class).topic();
            handlerMap.put(topic, msgHandler);
        });
    }
}
