package chat.wisechat.redis.service.impl;

import chat.wisechat.redis.service.RedisAutoIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author Siberia.Hu
 * @date 2025/1/23
 */
@Slf4j
@Service
public class RedisAutoIndexServiceImpl implements RedisAutoIndexService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String autoIndex() {
        String key = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        Long autoID = redisTemplate.opsForValue().increment(key, 1);
        // 第一个就设置key的过期时间
        if (autoID != null && autoID == 1) {
            log.info("set expire time");
            redisTemplate.expire(key, 10, TimeUnit.SECONDS);
        }
        log.info(key + ":" + autoID);
        return String.valueOf(autoID);
    }
}
