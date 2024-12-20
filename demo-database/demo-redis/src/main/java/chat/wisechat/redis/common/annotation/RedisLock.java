package chat.wisechat.redis.common.annotation;

import chat.wisechat.redis.common.constants.RedisLockTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Siberia.Hu
 * @date 2024/12/20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RedisLock {

    /**
     * 特定参数识别
     */
    int lockFiled() default 0;

    /**
     * 超时重试次数
     */
    int tryCount() default 3;

    /**
     * 自定义加锁类型
     */
    RedisLockTypeEnum typeEnum();

    /**
     * 释放时间，秒 s 单位
     */
    long lockTime() default 10;

}
