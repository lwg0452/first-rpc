package com.weiguangli.firstrpc.provicerdemo;

import com.weiguangli.firstrpc.network.RpcProvider;
import com.weiguangli.firstrpc.serialize.Serializer;

public class RpcProviderDemo {

    public static void main(String[] args) {
        RpcProvider rpcProvider = new RpcProvider(new Serializer());
        while (true) {
            rpcProvider.produceRpcRequest();
        }
    }
}
