package chat.wisechat.mqtt.controller;

import chat.wisechat.mqtt.service.ProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.mqtt.controller
 * @Date 2024/9/18 10:49
 */
@RestController
@RequestMapping("/producer")
public class ProducerController {
    @Resource
    private ProducerService producerService;

    @GetMapping("sendMsg")
    public String sendMsg(@RequestParam(name = "msg") String msg) {
        return producerService.sendMsg(msg);
    }
}
