package chat.wisechat.mapping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Siberia.Hu
 * @Date 2025/6/26 10:33
 */
@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {


    @PostMapping("/mapping")
    public String mapper() {
        log.info("请求");
        return "hello";
    }

}
