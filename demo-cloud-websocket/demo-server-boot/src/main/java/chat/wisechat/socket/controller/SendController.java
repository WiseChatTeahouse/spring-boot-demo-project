package chat.wisechat.socket.controller;

import chat.wisechat.socket.publisher.WsPublisher;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.socket.controller
 * @Date 2025/5/3 21:24
 */
@Slf4j
@RestController
@RequestMapping("/ws")
public class SendController {

    @Resource
    private WsPublisher wsPublisher;

    @PostMapping("/send")
    public void send(@RequestParam(name = "msg") String msg, @RequestParam(name = "id") String id) {
        try {
            Map<String, String> message = new HashMap<>();
            message.put("id", id);
            message.put("msg", msg);
            wsPublisher.send(JSON.toJSONString(message));
        } catch (Exception e) {
            log.error("异常", e);
        }
    }

}
