package chat.wisechat.server.server;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Siberia.Hu
 * @Date 2025/3/3 15:21
 */
@Slf4j
@Component
@ServerEndpoint("/websocket-server/{clientID}")
public class WebSocketServer {

    private Session session;

    private static AtomicInteger onlineCount = new AtomicInteger();

    private static final CopyOnWriteArrayList<WebSocketServer> webSocketServers = new CopyOnWriteArrayList<>();
    private static final ConcurrentHashMap<String, Session> clients = new ConcurrentHashMap<>();

    /**
     * 服务端与客户端连接成功时执行
     *
     * @param session 会话
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        String clientID = session.getPathParameters().get("clientID");
        //接入的客户端+1
        int count = onlineCount.incrementAndGet();
        //集合中存入客户端对象+1
        webSocketServers.add(this);
        clients.put(clientID, session);
        log.info("与客户端连接成功，当前连接的客户端数量为：{}", count);
    }

    /**
     * 收到客户端的消息时执行
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自客户端的消息，客户端地址：{}，消息内容：{}", session.getMessageHandlers(), message);
    }

    /**
     * 连接发生报错时执行
     *
     * @param session   会话
     * @param throwable 报错
     */
    @OnError
    public void onError(Session session, @NonNull Throwable throwable) {
        log.error("连接发生报错");
        throwable.printStackTrace();
    }

    /**
     * 连接断开时执行
     */
    @OnClose
    public void onClose() {
        //接入客户端连接数-1
        int count = onlineCount.decrementAndGet();
        //集合中的客户端对象-1
        webSocketServers.remove(this);
        log.info("服务端断开连接，当前连接的客户端数量为：{}", count);
    }


    /**
     * 向客户端推送消息
     *
     * @param message 消息
     */
    public void sendMessage(String message, String clientID) {
        Session session1 = clients.get(clientID);
        if (null == session1){
            return;
        }
        session1.getAsyncRemote().sendText(message);
    }


    /**
     * 群发消息
     *
     * @param message 消息
     */
    public void sendMessageToAll(String message) {
        for (WebSocketServer wsServer : webSocketServers) {
            wsServer.sendMessage(message,"0gh25PRew7");
        }
    }


}
