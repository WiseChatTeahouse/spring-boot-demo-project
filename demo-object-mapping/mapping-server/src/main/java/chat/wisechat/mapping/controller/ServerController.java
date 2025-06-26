package chat.wisechat.mapping.controller;

import chat.wisechat.mapping.feign.ClientFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Siberia.Hu
 * @Date 2025/6/26 10:33
 */
@Slf4j
@RestController
@RequestMapping("/server")
public class ServerController {

    @Resource
    private ClientFeign clientFeign;

    @GetMapping("/mapper")
    public void mapper() {
        String mapper = clientFeign.mapper();
        log.info("mapper= {}", mapper);
    }
}
