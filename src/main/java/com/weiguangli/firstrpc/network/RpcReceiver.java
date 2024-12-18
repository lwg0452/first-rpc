package com.weiguangli.firstrpc.network;

import com.weiguangli.firstrpc.entity.RpcRequest;
import com.weiguangli.firstrpc.entity.RpcResponse;
import com.weiguangli.firstrpc.serialize.Serializer;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class RpcReceiver {

    private Serializer serializer;

    public RpcReceiver(Serializer serializer) {
        this.serializer = serializer;
    }

    public void produceRpcRequest() {
        try {
            // 创建一个 ServerSocket，监听端口 5555
            int port = 5555;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port: " + port);

            // 等待客户端连接
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected: " + clientSocket.getInetAddress());

            // 获取输入输出流，用于通信
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

            RpcRequest request = (RpcRequest) in.readObject();
            Object result = handleRequest(request);
            RpcResponse response = RpcResponse
                    .builder()
                    .requestId(request.getRequestId())
                    .result(result)
                    .build();

            //向客户端发送响应
            out.writeObject(response);
            out.flush();

            // 关闭连接
            clientSocket.close();
            serverSocket.close();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object handleRequest(RpcRequest request) throws Exception {

        Class<?> serviceInterface = Class.forName(request.getInterfaceName());


        // TODO: 替换成获取缓存中的对象
        Object serviceInstance = serviceInterface.newInstance();

        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        Method method = serviceInterface.getMethod(methodName, parameterTypes);
        Object result = method.invoke(serviceInstance, parameters);

        System.out.println("远程调用结果:" + result);
        return result;
    }
}
