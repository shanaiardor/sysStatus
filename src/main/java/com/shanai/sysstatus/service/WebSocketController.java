package com.shanai.sysstatus.service;

import com.shanai.sysstatus.utils.WebSocketMapUtil;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.UUID;

@ServerEndpoint("/websocket.ws")
public class WebSocketController {

    @OnOpen
    public void onOpen(Session session ) {

        WebSocketMapUtil.put(session.getId(),session);
        System.out.println("连接打开了OPEN");
    }

    /**
     * 收到客户端消息时触发
     */
    @OnMessage
    public void onMessage(Session session, String key) throws IOException {
        //向客户端返回发送过来的消息
        System.out.println("发送一条消息：--" + key);
        session.getBasicRemote().sendText(key);//推送发送的消息
    }

    /**
     * 异常时触发
     */
    @OnError
    public void onError(Throwable throwable, Session session) {
        WebSocketMapUtil.remove(session.getId());
    }

    /**
     * 关闭连接时触发
     */
    @OnClose
    public void onClose(Session session) {
        WebSocketMapUtil.remove(session.getId());
        System.out.println("连接关闭了~~~~(>_<)~~~~");
    }


}