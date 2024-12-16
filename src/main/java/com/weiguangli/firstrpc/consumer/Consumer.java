package com.weiguangli.firstrpc.consumer;

import com.weiguangli.firstrpc.helloapi.HelloWorld;
import com.weiguangli.firstrpc.helloapi.IHelloWorld;
import com.weiguangli.firstrpc.proxy.RpcProxyHandler;
import java.lang.reflect.Proxy;

public class Consumer {

    public static void main(String[] args) {
        RpcProxyHandler proxy = new RpcProxyHandler(new HelloWorld());
        IHelloWorld helloWorld = (IHelloWorld) Proxy.newProxyInstance(
            IHelloWorld.class.getClassLoader(),
            IHelloWorld.class.getInterfaces(),
            proxy
        );
        helloWorld.hi("小嘟");
    }

}
