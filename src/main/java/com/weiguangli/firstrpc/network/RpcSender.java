package com.weiguangli.firstrpc.network;

import com.weiguangli.firstrpc.entity.RpcMessage;
import com.weiguangli.firstrpc.entity.RpcResponse;
import com.weiguangli.firstrpc.serialize.Serializer;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RpcSender {

    private Serializer serializer;

    public RpcSender(Serializer serializer) {
        this.serializer = serializer;
    }

    public Object send(RpcMessage request, String addr, int port) {

        Object result = null;
        // 连接到服务器指定 ip 和端口
        try (Socket socket = new Socket(addr, port)) {
            System.out.println("Connected to server");

            //获取输入输出流
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // 发送rpc请求到服务端
            out.writeObject(request);
            out.flush();

            // 接受服务端响应
            RpcResponse response = (RpcResponse) in.readObject();
            result = response.getResult();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
