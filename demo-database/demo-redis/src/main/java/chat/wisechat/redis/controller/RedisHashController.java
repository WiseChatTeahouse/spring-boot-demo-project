package chat.wisechat.redis.controller;

import chat.wisechat.redis.service.RedisHashService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Siberia.Hu
 * @Date 2025/5/13 14:43
 */

@RestController
@RequestMapping("/hash")
public class RedisHashController {

    @Resource
    private RedisHashService redisHashService;

    @GetMapping("/add")
    public void add() {
        redisHashService.add();
    }
}
