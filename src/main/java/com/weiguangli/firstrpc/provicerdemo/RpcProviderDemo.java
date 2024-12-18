package com.weiguangli.firstrpc.provicerdemo;

import com.weiguangli.firstrpc.network.RpcReceiver;
import com.weiguangli.firstrpc.serialize.Serializer;

public class RpcProviderDemo {

    public static void main(String[] args) {
        RpcReceiver rpcProvider = new RpcReceiver(new Serializer());
        while (true) {
            rpcProvider.produceRpcRequest();
        }
    }
}
