package chat.wisechat.socket.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.socket.config
 * @Date 2025/5/3 21:16
 */
@Configuration
public class RabbitmqConfig {

    @Value("${spring.application.instance-id:default}")
    private String instanceId;

    // Fanout Exchange（广播模式）
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("service.fanout");
    }

    // 动态创建队列，名称为 service.queue.${instance-id}
    @Bean
    public Queue dynamicQueue() {
        return new Queue("service.queue." + instanceId);
    }

    // 将动态队列绑定到 fanout exchange
    @Bean
    public Binding binding(FanoutExchange fanoutExchange, Queue dynamicQueue) {
        return BindingBuilder.bind(dynamicQueue).to(fanoutExchange);
    }

}
