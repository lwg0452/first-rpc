package com.weiguangli.firstrpc.rpc;

import com.weiguangli.firstrpc.entity.RpcRequest;

import java.lang.reflect.Method;

public class RpcProcessor {

    public Object invoke(RpcRequest request) throws Exception {

        Class<?> serviceInterface = Class.forName(request.getInterfaceName());

        // TODO: 替换成获取缓存中的对象
        Object serviceInstance = serviceInterface.newInstance();

        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        Method method = serviceInterface.getMethod(methodName, parameterTypes);
        Object result = method.invoke(serviceInstance, parameters);

        System.out.println("远程调用结果:" + result);
        return result;
    }
}
