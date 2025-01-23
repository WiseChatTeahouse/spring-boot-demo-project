package chat.wisechat.redis.controller;

import chat.wisechat.redis.service.RedisAutoIndexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Siberia.Hu
 * @date 2025/1/23
 */
@RestController
@RequestMapping("/autoIndex")
public class RedisAutoIndexController {

    @Resource
    private RedisAutoIndexService redisAutoIndexService;

    @GetMapping("/build")
    public String autoIndex() {
        return redisAutoIndexService.autoIndex();
    }

}
