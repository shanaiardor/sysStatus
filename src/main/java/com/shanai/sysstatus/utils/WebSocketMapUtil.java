package com.shanai.sysstatus.utils;

import javax.websocket.Session;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WebSocketMapUtil {
    public static ConcurrentMap<String, Session> webSocketMap = new ConcurrentHashMap<>();

    public static void put(String key, Session myWebSocket) {
        webSocketMap.put(key, myWebSocket);
    }

    public static Session get(String key) {
        return webSocketMap.get(key);
    }

    public static void remove(String key) {
        webSocketMap.remove(key);
    }

    public static Collection<Session> getValues() {
        return webSocketMap.values();
    }
}