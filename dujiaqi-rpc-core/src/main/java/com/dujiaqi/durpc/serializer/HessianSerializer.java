package com.dujiaqi.durpc.serializer;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Hessian 序列化器
 * 优点：
 * 二进制序列化，序列化后的数据量较小，网络传输效率高。
 * 支持跨语言，适用于分布式系统中的服务调用。
 * 缺点：
 * 性能较 JSON 略低，因为需要将对象转换为二进制格式。
 * 对象必须实现 Serializable 接口，限制了可序列化的对象范围。
 */
public class HessianSerializer implements Serializer{
    @Override
    public <T> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput();
        hessianOutput.writeObject(object);
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        HessianInput hessianInput = new HessianInput(byteArrayInputStream);
        return (T) hessianInput.readObject(type);
    }
}
