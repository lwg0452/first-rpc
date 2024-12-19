package com.weiguangli.firstrpc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceInfo {
    private String addr;
    private int port;
}
