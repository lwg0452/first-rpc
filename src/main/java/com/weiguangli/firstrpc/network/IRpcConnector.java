package com.weiguangli.firstrpc.network;

import com.weiguangli.firstrpc.entity.RpcRequest;
import com.weiguangli.firstrpc.entity.ServiceInfo;

public interface IRpcConnector {
    Object request(RpcRequest rpcRequest, ServiceInfo info);
}
