package chat.wisechat.socket.publisher;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.socket.publisher
 * @Date 2025/5/3 20:55
 */
@Component
public class WsPublisher {

    @Resource
    private AmqpTemplate amqpTemplate;


    public void send(String msg) throws Exception {
        amqpTemplate.convertAndSend("service.fanout", "", msg);
    }

}
