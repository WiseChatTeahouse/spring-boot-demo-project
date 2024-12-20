package chat.wisechat.redis.controller;

import chat.wisechat.redis.common.annotation.RedisLock;
import chat.wisechat.redis.common.constants.RedisLockTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Siberia.Hu
 * @date 2024/12/20
 */
@Slf4j
@RestController
@RequestMapping("/redisLock")
public class RedisLockController {

    private int count = 0;

    @GetMapping("/testRedisLock")
    @RedisLock(typeEnum = RedisLockTypeEnum.ONE, lockTime = 10)
    public void testRedisLock(@RequestParam("userId") Long userId) {

        try {
            log.info("开始装睡...");
            TimeUnit.SECONDS.sleep(10);
            count++;
            log.info("惊醒了... {}", count);
        } catch (InterruptedException e) {
            log.error("睡死了...", e);
        }
    }

}
