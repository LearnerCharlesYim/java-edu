package com.charles.sys.chat.component;

import com.charles.sys.chat.config.WebSocketConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@ServerEndpoint(value = "/chat/{username}", configurator = WebSocketConfig.class)
@Component
public class WebSocketServer {

    private static int onlineCount = 0;

    private static final ConcurrentHashMap<String, WebSocketServer> serverMap = new ConcurrentHashMap<>();

    private Session session;

    private String ipAddr;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        Map<String, Object> userProperties = session.getUserProperties();
        this.ipAddr = (String) userProperties.get(WebSocketConfig.IP_ADDR);
        if (serverMap.containsKey(this.ipAddr)) {
            serverMap.remove(this.ipAddr);
            serverMap.put(this.ipAddr, this);
        } else {
            serverMap.put(this.ipAddr, this);
            addOnlineCount();
            log.info(this.ipAddr + "，已上线！");
        }
    }

    /**
     * 服务器接收客户端发来的消息
     *
     * @param message 消息
     * @param session 会话session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("服务器收到了用户" + ipAddr + "发来的消息：" + message);

        //方便前端测试
        sendMessage("服务器收到了用户" + ipAddr + "发来的消息：" + message);
    }

    /**
     * 给ip地址为ipAddr的客户端发送消息
     *
     * @param ipAddr  ip地址
     * @param message 消息
     */
    public static void sendMessage(String ipAddr, String message) {
        if (serverMap.containsKey(ipAddr)) {
            WebSocketServer webSocketServer = serverMap.get(ipAddr);
            webSocketServer.sendMessage(message);
        } else {
            log.error("发送失败，客户端未连接： " + ipAddr);
        }
    }

    /**
     * 服务器主动发送消息
     *
     * @param message 消息
     */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 获取在线人数
     *
     * @return 在线人数
     */
    public static int getOnlineCount() {
        return onlineCount;
    }

    @OnClose
    public void onClose() {
        if (serverMap.containsKey(ipAddr)) {
            serverMap.remove(ipAddr);
            subOnlineCount();
            log.info(ipAddr + "，已下线！");
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("用户" + ipAddr + "发生了错误，具体如下：" + throwable.getMessage());
    }

    private static synchronized void subOnlineCount() {
        onlineCount--;
    }

    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    public static WebSocketServer get(String ipAddr) {
        return serverMap.get(ipAddr);
    }

    public static ConcurrentHashMap<String, WebSocketServer> getMap() {
        return serverMap;
    }

    public static boolean isOnline(String ipAddr) {
        return serverMap.containsKey(ipAddr);
    }

}
