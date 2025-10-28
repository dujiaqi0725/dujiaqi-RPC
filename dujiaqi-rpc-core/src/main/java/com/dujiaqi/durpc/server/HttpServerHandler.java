package com.dujiaqi.durpc.server;

import com.dujiaqi.durpc.RpcApplication;
import com.dujiaqi.durpc.model.RpcRequest;
import com.dujiaqi.durpc.model.RpcResponse;
import com.dujiaqi.durpc.registry.LocalRegistry;
import com.dujiaqi.durpc.serializer.JdkSerializer;
import com.dujiaqi.durpc.serializer.Serializer;
import com.dujiaqi.durpc.serializer.SerializerFactory;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

public class HttpServerHandler implements Handler<HttpServerRequest> {
    @Override
    public void handle(HttpServerRequest request) {

        //指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 记录日志
        System.out.println("Received request: " + request.method() + " " + request.uri());

        //异步处理 HTTP 请求
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                // 将请求反序列化为对象
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //构造响应结果
            RpcResponse rpcResponse = new RpcResponse();
            // 如果请求为空
            if (rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null");
                // 发送响应后的结果
                doResponse(request,rpcResponse,serializer);
                return;
            }

            try {
                // 从反序列化后的请求中获取需要调用的服务实现类，并通过反射调用
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(),rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
                // 封装返回结果
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }

            //响应
            doResponse(request,rpcResponse,serializer);

        });
    }

    /**
     * 响应
     * @param request 需要响应的请求
     * @param rpcResponse 响应的结果
     * @param serializer 序列化工具
     */
    void doResponse(HttpServerRequest request,RpcResponse rpcResponse , Serializer serializer){
        HttpServerResponse httpServerResponse = request.response()
                .putHeader("content-type" , "application/json");

        try {
            // 序列化
            byte[] serialized = serializer.serialize(rpcResponse);
            // 发送响应
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }

    }

}
