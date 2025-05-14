package chat.wisechat.okhttp.controller;

import chat.wisechat.okhttp.config.SendClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Siberia.Hu
 * @Date 2025/5/12 18:03
 */
@Slf4j
@RestController
@RequestMapping("/send")
public class SendController {

    @Resource
    private SendClient sendClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/send")
    public void send() throws JsonProcessingException {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "zhangsan");
        requestBody.put("age", 18);
        String s = sendClient.doPost("http://127.0.0.1:8081/resp/resp", null, objectMapper.writeValueAsString(requestBody));
        log.info(s);
    }


}
