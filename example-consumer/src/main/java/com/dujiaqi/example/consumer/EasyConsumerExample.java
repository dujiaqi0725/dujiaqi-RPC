package com.dujiaqi.example.consumer;

import com.dujiaqi.durpc.proxy.ServiceProxyFactory;
import com.dujiaqi.example.common.model.User;
import com.dujiaqi.example.common.service.UserService;

public class EasyConsumerExample {

    public static void main(String[] args) {
        // 静态代理
        //UserService userService = new UserServiceProxy();
        //动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("dujiaqi");

        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        }else {
            System.out.println("user == null");
        }
    }

}
