package com.songzhen.howcool;

import cn.hutool.core.lang.Console;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {

    private final static Logger logger = LoggerFactory.getLogger(BIOServer.class);

    public static void main(String[] args) throws IOException {

        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(9999);
        logger.info("服务器启动了，端口：9999");

        while (true) {
            final Socket socket = serverSocket.accept();
            logger.info("服务器收到一个连接，当前线程ID="+Thread.currentThread().getId());
            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }

    }


    public static void handler(Socket socket) {

        try {
            logger.info("服务器待处理，当前线程ID="+Thread.currentThread().getId());
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true) {
                logger.info("服务器处理中，当前线程ID="+Thread.currentThread().getId());
                int read = inputStream.read(bytes);
                if (-1 != read) {
                    Console.print(new String(bytes));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("socket getInputSteam error", e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.error("socket close error", e);
            }
        }


    }
}
