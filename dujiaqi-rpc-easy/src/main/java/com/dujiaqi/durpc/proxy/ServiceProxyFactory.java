package com.dujiaqi.durpc.proxy;

import java.lang.reflect.Proxy;

/**
 * 服务代理工厂 (用于创建代理对象)
 */
public class ServiceProxyFactory {

    /**
     *
     * 根据服务类获取代理对象
     *
     * @param serviceClass 服务类
     * @return
     * @param <T>
     */
    public static <T> T getProxy(Class<T> serviceClass){
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }

}
