package chat.wisechat.log.controller;

import chat.wisechat.log.entity.User;
import chat.wisechat.log.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private Map<String, UserService> userServiceMap;

    @GetMapping("/getUser")
    public User getUser() {
        log.info("UserController getUser");
        int size = userServiceMap.size();
        log.info(String.valueOf(size));
        userServiceMap.keySet().forEach(System.out::println);
        return userServiceMap.get("userServiceImpl").getUser();
    }
}
