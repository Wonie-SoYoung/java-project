package com.soyoung.photo.config;

import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;


@Component
// 你的WebSocket访问地址
@ServerEndpoint("/webSocket")
public class WebSocket {
    private Session session;
    //定义Websocket容器，储存session
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    //对应前端的一些事件
    //建立连接
    @OnOpen
    public void opOpen(Session session) {
        this.session = session;
        int i=0;
        for (WebSocket webSocket:webSocketSet){
            i++;
        }
        System.err.println("数量:"+i);
        System.out.println("建立连接");
        System.out.println(session.getId());
        System.out.println(session.getBasicRemote());
        webSocketSet.add(this);
    }

    //关闭连接
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
    }

    //接收消息
    @OnMessage
    public void onMessage(String message) {
    }

    //发送消息
    public void sendMessage(String message) {
        //遍历储存的Websocket
        for (WebSocket webSocket : webSocketSet) {
            //群发发送
            try {
                webSocket.session.getBasicRemote().sendText(message);
                System.out.println("发送消息！");
                System.out.println(webSocket.session.getId());
                System.out.println(webSocket.session.getBasicRemote());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
