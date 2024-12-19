package com.weiguangli.firstrpc.providerdemo;

import com.weiguangli.firstrpc.listener.RpcListener;
import com.weiguangli.firstrpc.network.IRpcAcceptor;
import com.weiguangli.firstrpc.network.socket.SocketRpcAcceptor;
import com.weiguangli.firstrpc.rpc.RpcProcessor;

public class RpcProviderDemo {

    public static void main(String[] args) {
        RpcListener listener = new RpcListener(new RpcProcessor(), 5555);
        listener.listen();
    }
}
