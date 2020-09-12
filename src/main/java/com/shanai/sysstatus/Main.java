package com.shanai.sysstatus;

import com.jfinal.json.Json;
import com.jfinal.server.undertow.UndertowServer;
import com.shanai.sysstatus.config.InitConfig;
import com.shanai.sysstatus.dto.MonitorInfoBean;
import com.shanai.sysstatus.service.IMonitorService;
import com.shanai.sysstatus.service.MonitorServiceImpl;
import com.shanai.sysstatus.utils.WebSocketMapUtil;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        IMonitorService monitorService = new MonitorServiceImpl();

        monitorService.listen(monitorInfoBean -> {
            Collection<Session> values = WebSocketMapUtil.getValues();
            for (Session value : values) {
                try {
                    value.getBasicRemote().sendText(Json.getJson().toJson(monitorInfoBean));
                } catch (IOException e) {
                    WebSocketMapUtil.remove(value.getId());
                    e.printStackTrace();
                }
            }
            return true;
        },1000L);

        UndertowServer.create(InitConfig.class)
                .configWeb( builder -> {
                    // 配置 WebSocket，MyWebSocket 需使用 ServerEndpoint 注解
                    builder.addWebSocketEndpoint("com.shanai.sysstatus.service.WebSocketController");
                })
                .start();
    }
}
