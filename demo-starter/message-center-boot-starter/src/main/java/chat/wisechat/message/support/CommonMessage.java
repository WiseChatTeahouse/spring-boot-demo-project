package chat.wisechat.message.support;

import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.io.Serializable;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.message.support
 * @Date 2025/3/3 20:35
 */
@Setter
public class CommonMessage<T> implements Message<T>, Serializable {

    private final T payload;
    private final MessageHeaders headers;

    public CommonMessage(T payload, MessageHeaders headers) {
        this.payload = payload;
        this.headers = headers;
    }


    @NotNull
    @Override
    public T getPayload() {
        return this.payload;
    }

    @NotNull
    @Override
    public MessageHeaders getHeaders() {
        return this.headers;
    }
}
