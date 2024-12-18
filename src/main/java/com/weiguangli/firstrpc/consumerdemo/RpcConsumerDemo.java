package com.weiguangli.firstrpc.consumerdemo;

import com.weiguangli.firstrpc.helloapi.HelloWorld;
import com.weiguangli.firstrpc.helloapi.IHelloWorld;
import com.weiguangli.firstrpc.network.RpcSender;
import com.weiguangli.firstrpc.proxy.RpcProxyHandler;
import com.weiguangli.firstrpc.serialize.Serializer;

import java.lang.reflect.Proxy;

public class RpcConsumerDemo {

    public static void main(String[] args) {
        RpcSender sender = new RpcSender(new Serializer());
        RpcProxyHandler proxy = new RpcProxyHandler(new HelloWorld(), sender, "localhost", 5555);
        IHelloWorld helloWorldProxy = (IHelloWorld) Proxy.newProxyInstance(
            HelloWorld.class.getClassLoader(),
            HelloWorld.class.getInterfaces(),
            proxy
        );
        String res = helloWorldProxy.hi("小嘟");
        System.out.println(res);

        res = helloWorldProxy.hi("小抖");
        System.out.println(res);

        res = helloWorldProxy.hi("柯基小宝");
        System.out.println(res);
    }

}
