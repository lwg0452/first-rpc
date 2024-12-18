package com.weiguangli.firstrpc.protocol;

public class FirstRpcHeader {

    byte[] header;

    public FirstRpcHeader() {
        header = new byte[16];
    }

    public byte[] generateProtocolHeader() {
        return header;
    }
}
