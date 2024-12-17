package com.weiguangli.firstrpc.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {

    // 将对象序列化成字节数组
    public byte[] encode(Object object) throws IOException {
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        }
    }

    // 从字节数组中反序列化出对象
    public Object decode(byte[] data, int offset, int len) throws IOException, ClassNotFoundException {
        Object target = null;
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data, 0, len))) {
            target = objectInputStream.readObject();
        }
        return target;
    }

}
