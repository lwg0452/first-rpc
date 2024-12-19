package com.weiguangli.firstrpc.registry.zk;

import com.weiguangli.firstrpc.entity.ServiceInfo;
import com.weiguangli.firstrpc.registry.IServiceDiscover;

public class ZkServiceDiscover implements IServiceDiscover {

    @Override
    public ServiceInfo getServiceInfo(String interfaceName) {
        return new ServiceInfo("localhost", 5555);
    }
}
