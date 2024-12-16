package com.weiguangli.firstrpc.proxy;

import com.weiguangli.firstrpc.entity.RpcMessage;
import com.weiguangli.firstrpc.entity.RpcRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RpcProxyHandler implements InvocationHandler {

    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcMessage rpcRequest = RpcRequest
            .builder()
            .requestId(UUID.randomUUID().toString())
            .interfaceName(method.getDeclaringClass().getName())
            .methodName(method.getName())
            .parameters(args)
            .parameterTypes(method.getParameterTypes())
            .build();
        Object res = method.invoke(target, args);
        return res;
    }
}
