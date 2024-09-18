package chat.wisechat.mqtt.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.mqtt.annotation
 * @Date 2024/9/18 9:57
 */
@Component
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MqttListener {
    /**
     * 订阅的topic
     */
    String topic();
}
