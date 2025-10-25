package com.dujiaqi.example.consumer;

import com.dujiaqi.durpc.proxy.ServiceProxyFactory;
import com.dujiaqi.example.common.model.User;
import com.dujiaqi.example.common.service.UserService;

/**
 * 简易服务消费者示例
 */
public class ConsumerExample {

    public static void main(String[] args) {
        // 获取代理
        UserService userService = ServiceProxyFactory.getMockProxy(UserService.class);
        User user = new User();
        user.setName("dujiaqi");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null){
            System.out.println(newUser.getName());
        }else {
            System.out.println("user == null");
        }
        long number = userService.getNumber();
        System.out.println(number);
    }

}
