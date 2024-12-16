package com.weiguangli.firstrpc.entity;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RpcResponse implements Serializable, RpcMessage {
    private String requestId;
    private Object result;
}