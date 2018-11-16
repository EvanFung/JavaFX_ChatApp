package server;

import common.ConstantValue;
import common.SendHelper;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server {
    private final ServerSocket server;
    private final ExecutorService pool;

    public Server() throws IOException {
        server = new ServerSocket(ConstantValue.SERVER_PORT);
        pool = Executors.newFixedThreadPool(ConstantValue.MAX_POOL_SIZE);
    }

    public void start() {
        try {
            ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
            // Watch dog.  Exception??
            schedule.scheduleAtFixedRate(new SocketSchedule(), 10, ConstantValue.TIME_OUT, TimeUnit.SECONDS);
            while (true) {
                pool.execute(new SocketDispatcher(server.accept()));
                System.out.println("ACCEPT A CLIENT AT " + new Date());
            }
        } catch (IOException e) {
            pool.shutdown();
        }
    }


    public static void main(String[] args) {
        try {
            new Server().start();
        } catch (IOException e) {
            System.out.println("server start failed");
        }
    }
}
