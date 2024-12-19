package com.weiguangli.firstrpc.network.socket;

import com.weiguangli.firstrpc.entity.RpcRequest;
import com.weiguangli.firstrpc.entity.RpcResponse;
import com.weiguangli.firstrpc.network.IRpcAcceptor;
import com.weiguangli.firstrpc.rpc.RpcProcessor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketRpcAcceptor implements IRpcAcceptor {

    int port;
    RpcProcessor rpcProcessor;

    public SocketRpcAcceptor(int port, RpcProcessor rpcProcessor) {
        this.port = port;
        this.rpcProcessor = rpcProcessor;
    }

    @Override
    public void response() {
        try (ServerSocket socket = new ServerSocket(port)) {
            System.out.println("Server is listening on port: " + port);

            // 等待客户端连接
            Socket clientSocket = socket.accept();
            System.out.println("New client connected: " + clientSocket.getInetAddress());

            // 获取输入输出流，用于通信
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

            RpcRequest request = (RpcRequest) in.readObject();
            Object result = rpcProcessor.invoke(request);
            RpcResponse response = RpcResponse
                    .builder()
                    .result(result)
                    .build();

            //向客户端发送响应
            out.writeObject(response);
            out.flush();

            // 关闭连接
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
