package com.weiguangli.firstrpc.network.socket;

import com.weiguangli.firstrpc.entity.RpcRequest;
import com.weiguangli.firstrpc.entity.RpcResponse;
import com.weiguangli.firstrpc.entity.ServiceInfo;
import com.weiguangli.firstrpc.network.IRpcConnector;
import com.weiguangli.firstrpc.serialize.ISerializer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketRpcConnector implements IRpcConnector {

    @Override
    public Object request(RpcRequest rpcRequest, ServiceInfo info) {
        Object result = null;
        // 连接到服务器指定 ip 和端口
        try (Socket socket = new Socket(info.getAddr(), info.getPort())) {
            System.out.println("Connected to server");

            //获取输入输出流
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // 发送rpc请求到服务端
            out.writeObject(rpcRequest);
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
