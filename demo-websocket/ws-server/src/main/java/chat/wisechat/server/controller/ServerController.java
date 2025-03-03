package chat.wisechat.server.controller;

import chat.wisechat.server.server.WebSocketServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Siberia.Hu
 * @Date 2025/3/3 15:28
 */
@RestController
@RequestMapping("/server")
public class ServerController {

    @Resource
    private WebSocketServer webSocketServer;


    @PostMapping("/sendMsg")
    public void sendMsg() {
        webSocketServer.sendMessage("hello!", "0gh25PRew7");
    }
}
