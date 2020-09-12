package com.shanai.sysstatus.config;

import com.jfinal.config.*;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.shanai.sysstatus.controller.HelloController;

public class InitConfig extends JFinalConfig {
 



    @Override
    public void configConstant(Constants me) {
       me.setDevMode(true);
    }

    @Override
    public void configRoute(Routes me) {
       me.add("/hello", HelloController.class);

    }

    @Override
    public void configEngine(Engine me) {
    }

    @Override
    public void configPlugin(Plugins me) {
    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers handlers) {
    }
}