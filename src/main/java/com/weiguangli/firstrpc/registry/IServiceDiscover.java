package com.weiguangli.firstrpc.registry;

import com.weiguangli.firstrpc.entity.ServiceInfo;

public interface IServiceDiscover {
    ServiceInfo getServiceInfo(String interfaceName);
}
