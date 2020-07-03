package com.example.demo.bio;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class BioClient {

    public static void main(String[] args) {
        new Thread(()->{
           try {
               Socket socket = new Socket("127.0.0.1", 3333);
               while (true) {
                   socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                   Thread.sleep(2000);
               }
           } catch (InterruptedException e) {
               e.printStackTrace();
           } catch (UnknownHostException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
        }).start();
    }

}
