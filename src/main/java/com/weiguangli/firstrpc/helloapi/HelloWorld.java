package com.weiguangli.firstrpc.helloapi;

public class HelloWorld implements IHelloWorld{

    @Override
    public String hello() {
        return null;
    }

    @Override
    public String hi(String name) {
        return "Hi, " + name;
    }
}
