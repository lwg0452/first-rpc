package com.weiguangli.firstrpc.entity;


import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RpcRequest implements Serializable, IRpcMessage {
    private String interfaceName;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;


}
