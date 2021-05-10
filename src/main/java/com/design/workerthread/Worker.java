package com.design.workerthread;

import java.util.Random;

public class Worker extends Thread {

    private static final Random random = new Random(System.currentTimeMillis());
    private final PackageChannel channel;

    public Worker(String name, PackageChannel channel) {
        super(name); this.channel = channel;
    }


    @Override
    public void run() {
        while (true) {
            channel.take().execute();

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
