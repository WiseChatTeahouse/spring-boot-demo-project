package chat.wisechat.pgsql.controller;

import chat.wisechat.pgsql.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/getUser")
    public String getUser() {
        return userService.getUser();
    }

    @PostMapping("/saveUser")
    public String saveUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        return userService.saveUser(username, password);
    }

}
