package com.weiguangli.firstrpc.proxy;

import com.weiguangli.firstrpc.entity.RpcMessage;
import com.weiguangli.firstrpc.entity.RpcRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

import com.weiguangli.firstrpc.network.RpcSender;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class RpcProxyHandler implements InvocationHandler {

    private Object target;
    private RpcSender rpcSender;
    private String addr;
    private int port;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcMessage rpcRequest = RpcRequest
            .builder()
            .requestId(UUID.randomUUID().toString())
            .interfaceName(target.getClass().getName())
            .methodName(method.getName())
            .parameters(args)
            .parameterTypes(method.getParameterTypes())
            .build();
        return rpcSender.send(rpcRequest, addr, port);
    }
}
