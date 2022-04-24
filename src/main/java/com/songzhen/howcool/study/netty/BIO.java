package com.songzhen.howcool.study.netty;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BIO {

    static class HandleTask implements Runnable {

        private Socket socket = null;

        public HandleTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            byte[] message = new byte[10];
            try {
                int read = socket.getInputStream().read(message);
                if (-1 != read) {
                    System.out.println(new String(message, Charset.forName("utf-8")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new HandleTask(socket)).start();
        }

    }

}
