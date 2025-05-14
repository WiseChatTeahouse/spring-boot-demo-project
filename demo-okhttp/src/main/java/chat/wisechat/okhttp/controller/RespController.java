package chat.wisechat.okhttp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author Siberia.Hu
 * @Date 2025/5/14 10:59
 */
@Slf4j
@RestController
@RequestMapping("/resp")
public class RespController {


    @PostMapping("/resp")
    public String resp(@RequestBody Map<String, Object> body) {
        log.info("{}", body);

        return "hello";
    }

}
