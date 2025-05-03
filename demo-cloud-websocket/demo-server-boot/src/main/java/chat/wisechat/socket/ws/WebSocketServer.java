package chat.wisechat.socket.ws;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
@ServerEndpoint("/websocket/{sid}")
public class WebSocketServer {


    private static RedisTemplate<String, String> redisTemplate;

    public WebSocketServer() {
    }

    @Getter
    private static final CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    @Getter
    private  String sid = "";


    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {

        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        log.info("有新窗口开始监听:" + sid + ", 当前在线人数为" + getOnlineCount());
        this.sid = sid;
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口" + sid + "的信息:" + message);
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                log.error("接收消息异常", e);
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误", error);
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        log.info("推送消息到窗口" + sid + "，推送内容:" + message);
        for (WebSocketServer item : webSocketSet) {
            try {
                if (sid == null) {
                    item.sendMessage(message); // 广播消息
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message); // 发送消息给指定人
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public synchronized String getOnlineCount() {
        return redisTemplate.opsForValue().get("login:user:count");
    }

    public synchronized void addOnlineCount() {
        redisTemplate.opsForValue().increment("login:user:count");
    }

    public synchronized void subOnlineCount() {
        redisTemplate.opsForValue().decrement("login:user:count");
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        WebSocketServer.redisTemplate = redisTemplate;
    }

}
