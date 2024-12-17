package com.weiguangli.firstrpc.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RpcSender {

    public static void main(String[] args) {

        try {
            // 连接到服务器指定 ip 和端口
            Socket socket = new Socket("localhost", 5555);
            System.out.println("Connected to server");

            //获取输入输出流
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            // 发送字节数组到服务端
            String message = "aaa";
            out.write(message.getBytes());
            out.flush();

            // 接受服务端响应
            byte[] receiveData = new byte[1024];
            int bytesRead = in.read(receiveData);
            System.out.println("Received from server: " + new String(receiveData, 0, bytesRead));

            // 关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
