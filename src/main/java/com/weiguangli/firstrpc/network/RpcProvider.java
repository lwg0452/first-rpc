package com.weiguangli.firstrpc.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RpcProvider {

    public static void main(String[] args) {
        try {
            // 创建一个 ServerSocket，监听端口 5555
            int port = 5555;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port: " + port);

            // 等待客户端连接
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected: " + clientSocket.getInetAddress());

            // 获取输入输出流，用于通信
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();

            byte[] receiveData = new byte[1024];
            int bytesRead;
            while((bytesRead = in.read(receiveData)) != -1) {
                System.out.println("Received data from client: " + new String(receiveData, 0, bytesRead));

                //向客户端发送字节数组响应
                out.write("Server received: ".getBytes());
                out.write(receiveData, 0, bytesRead);
                out.flush();
            }

            // 关闭连接
            clientSocket.close();
            serverSocket.close();



        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
