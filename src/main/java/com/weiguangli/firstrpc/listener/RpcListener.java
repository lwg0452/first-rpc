package com.weiguangli.firstrpc.listener;

import com.weiguangli.firstrpc.network.IRpcAcceptor;
import com.weiguangli.firstrpc.network.socket.SocketRpcAcceptor;
import com.weiguangli.firstrpc.rpc.RpcProcessor;

public class RpcListener {

    private RpcProcessor rpcProcessor;
    private int port;

    public RpcListener(RpcProcessor processor, int port) {
        this.rpcProcessor = processor;
        this.port = port;
    }

    public void listen() {
        IRpcAcceptor acceptor = new SocketRpcAcceptor(this.port, this.rpcProcessor);
        while (true) {
            acceptor.response();
        }
    }
}
