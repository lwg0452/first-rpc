package com.weiguangli.firstrpc.rpc;

import com.weiguangli.firstrpc.entity.RpcRequest;
import com.weiguangli.firstrpc.entity.ServiceInfo;
import com.weiguangli.firstrpc.network.IRpcConnector;
import com.weiguangli.firstrpc.registry.IServiceDiscover;

public class RpcInvoker {

    IRpcConnector connector;
    IServiceDiscover discover;

    public RpcInvoker(IRpcConnector connector, IServiceDiscover discover) {
        this.connector = connector;
        this.discover = discover;
    }

    public Object invoke(RpcRequest rpcRequest) {
        ServiceInfo info = discover.getServiceInfo(rpcRequest.getInterfaceName());
        return connector.request(rpcRequest, info);
    }
}
