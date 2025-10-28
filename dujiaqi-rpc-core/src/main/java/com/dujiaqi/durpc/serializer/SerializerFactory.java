package com.dujiaqi.durpc.serializer;

import com.dujiaqi.durpc.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * 序列化器工厂 (用于获取序列化器对象)
 */
public class SerializerFactory {

    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();
    /**
     * 获取示例
     * @param key 要获取的序列化器的key
     * @return
     */
    public static Serializer getInstance(String key){
        return SpiLoader.getInstance(Serializer.class,key);
    }

}
