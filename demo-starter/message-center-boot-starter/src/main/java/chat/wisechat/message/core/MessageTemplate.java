package chat.wisechat.message.core;

import chat.wisechat.message.MessageCenterProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

import javax.annotation.Resource;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.message.core
 * @Date 2025/3/3 20:33
 */
@Slf4j
public class MessageTemplate {

    @Resource
    private OkHttpHelper okHttpHelper;
    @Resource
    private MessageCenterProperties messageCenterProperties;

    public String send(Message<?> message) {
        return okHttpHelper.post(messageCenterProperties.getFeishuUrl(), message);
    }
}
