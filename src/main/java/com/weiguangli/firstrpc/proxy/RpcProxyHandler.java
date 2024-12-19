package com.weiguangli.firstrpc.proxy;

import com.weiguangli.firstrpc.entity.RpcRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import com.weiguangli.firstrpc.rpc.RpcInvoker;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RpcProxyHandler implements InvocationHandler {

    private Object target;
    RpcInvoker invoker;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        RpcRequest rpcRequest = RpcRequest
            .builder()
            .interfaceName(target.getClass().getName())
            .methodName(method.getName())
            .parameters(args)
            .parameterTypes(method.getParameterTypes())
            .build();
        return invoker.invoke(rpcRequest);
    }
}
