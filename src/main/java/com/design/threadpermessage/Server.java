package com.design.threadpermessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class Server {

    private static int DEFAULT_PORT = 12345;
    private static ServerSocket server;

    public static void start() throws IOException {
        start(DEFAULT_PORT);
    }

    public static void start(int port) throws IOException {
        if (server != null) {
            return;
        }

        try {
            server = new ServerSocket(port);
            while (true) {
                Socket socket = server.accept();

                StopWatch stopWatch = new StopWatch();
                stopWatch.start("Server 监听任务开始");
                log.info("服务开始");
                new Thread(new ServerHandler(socket)).start();
                stopWatch.stop();
                log.info("stop watch:{}" , stopWatch.prettyPrint());

            }
        } finally {
            if (server != null) {
                System.out.println("服务器已关闭!");
                server.close();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        new Thread(() -> {
            try {
                Server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
