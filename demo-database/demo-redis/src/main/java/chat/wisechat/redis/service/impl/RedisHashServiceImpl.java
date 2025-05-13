package chat.wisechat.redis.service.impl;

import chat.wisechat.redis.service.RedisHashService;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Siberia.Hu
 * @Date 2025/5/13 14:45
 */
@Service
public class RedisHashServiceImpl implements RedisHashService {

    @Resource
    private RedisTemplate<String, Object> stringRedisTemplate;
    private static final AtomicInteger COUNT = new AtomicInteger(0);

    @Override
    public void add() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "张三");
        jsonObject.put("age", 18);
        jsonObject.put("idNum", COUNT.get());

        String jsonString = jsonObject.toJSONString();
        stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                RedisTemplate<String, Object> stringRedisTemplate = (RedisTemplate<String, Object>) operations;
                stringRedisTemplate.opsForHash().put("redis:hash:12508", String.valueOf(COUNT.get()), jsonString);

                //stringRedisTemplate.expire("redis:hash:12508", 5, TimeUnit.MINUTES);

                return null;
            }
        });

        Long expire = stringRedisTemplate.getExpire("redis:hash:12508");
        System.out.println("expire = " + expire);
        int andAdd = COUNT.getAndAdd(1);
        if (10 == andAdd) {
            COUNT.set(0);
        }
    }
}
