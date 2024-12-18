package com.weiguangli.firstrpc.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {

    // 将对象序列化成字节数组
    public byte[] encode(Object object) throws IOException {
        return null;
    }

    // 从字节数组中反序列化出对象
    public Object decode(InputStream in) throws IOException, ClassNotFoundException {
        Object target = null;
        try(ObjectInputStream objectInputStream = new ObjectInputStream(in)) {
            target = objectInputStream.readObject();
        }
        return target;
    }

}
