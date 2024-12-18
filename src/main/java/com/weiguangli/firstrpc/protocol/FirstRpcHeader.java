package com.weiguangli.firstrpc.protocol;

public class FirstRpcHeader {

    byte[] header;

    public byte[] generateProtocolHeader() {
        return header;
    }
}
