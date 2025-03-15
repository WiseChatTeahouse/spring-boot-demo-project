package chat.wisechat.client.controller;

import org.java_websocket.client.WebSocketClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Siberia.Hu
 * @Date 2025/3/3 15:41
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @Resource
    WebSocketClient wsClient;

    /**
     * 客户端发消息给服务端
     */
    @PostMapping("/send2server")
    public void websocket() {
        wsClient.send("Hello server...");
    }
}
