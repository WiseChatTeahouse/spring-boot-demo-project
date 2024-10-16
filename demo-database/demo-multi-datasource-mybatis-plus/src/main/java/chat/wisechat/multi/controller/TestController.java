package chat.wisechat.multi.controller;


import chat.wisechat.multi.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/getOne/{id}")
    public String getOne(@PathVariable("id") Integer id) {
        return testService.getOne();
    }

}
