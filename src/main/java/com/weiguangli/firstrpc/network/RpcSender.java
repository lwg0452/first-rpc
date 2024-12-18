package com.weiguangli.firstrpc.network;

import com.weiguangli.firstrpc.entity.RpcMessage;
import com.weiguangli.firstrpc.entity.RpcResponse;
import com.weiguangli.firstrpc.serialize.Serializer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RpcSender {

    Serializer serializer;

    public RpcSender(Serializer serializer) {
        this.serializer = serializer;
    }

    public Object send(RpcMessage request, String addr, int port) {

        Object result = null;
        try {
            // 连接到服务器指定 ip 和端口
            Socket socket = new Socket(addr, port);
            System.out.println("Connected to server");

            //获取输入输出流
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            // 发送字节数组到服务端
            out.write(serializer.encode(request));
            out.flush();

            // 接受服务端响应
            byte[] receiveData = new byte[22048];
            int bytesRead = in.read(receiveData);
            RpcResponse response = (RpcResponse) serializer.decode(receiveData, 0, bytesRead);
            result = response.getResult();
            // 关闭连接
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
