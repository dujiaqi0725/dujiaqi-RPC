package com.dujiaqi.durpc.serializer;

import java.io.IOException;

/**
 * 序列化接口
 */
public interface Serializer {

    /**
     * 序列化
     * @param object 需要被序列化的对象
     * @return 被序列化后的对象
     * @param <T> 需要被序列化的对象的类型
     * @throws IOException
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 反序列化
     * @param bytes 反序列化的对象
     * @param type 反序列化的对象的类型
     * @return 对象
     * @param <T>
     * @throws IOException
     */
    <T> T deserialize(byte[] bytes , Class<T> type) throws IOException;
}
