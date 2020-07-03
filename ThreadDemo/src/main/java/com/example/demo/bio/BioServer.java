package com.example.demo.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    public static void main(String[] args) throws IOException {
        // 设置服务器请求端口
        ServerSocket serverSocket = new ServerSocket(3333);
        new Thread(() -> {
            while (true) {
                try {
                    //阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();

                    new Thread(() -> {
                        try {
                            int len;
                            byte[] bytes = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            //按字节流读取数据
                            while ((len = inputStream.read(bytes)) != -1) {
                                System.out.println(new String(bytes, 0, len));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
