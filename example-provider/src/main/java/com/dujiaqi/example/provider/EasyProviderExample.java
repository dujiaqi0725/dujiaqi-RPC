package com.dujiaqi.example.provider;

import com.dujiaqi.example.common.service.UserService;
import com.dujiaqi.durpc.registry.LocalRegistry;
import com.dujiaqi.durpc.server.HttpServer;
import com.dujiaqi.durpc.server.VertxHttpServer;

public class EasyProviderExample {
    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName() , UserServiceImpl.class);
        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
