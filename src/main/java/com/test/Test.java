package com.test;

import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<Runnable> blockingDeque = new LinkedBlockingDeque<>();
        //SynchronousQueue<Object> objects = new SynchronousQueue<>(true);
        //SynchronousQueue<Object> objects = new SynchronousQueue<>();
        BlockingQueue<String> objects1 = new SynchronousQueue<>(false);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 30, TimeUnit.SECONDS, blockingDeque);
        Executor executor1 = Executors.newSingleThreadExecutor();
        Executor executor2 = Executors.newCachedThreadPool();
        Executor executor3 = Executors.newFixedThreadPool(10);



        /*for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println("投递：" + finalI);
                    objects1.put("====" + finalI);
                    System.out.println("等待揽收");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }

        Thread.sleep(1000);
        System.out.println("---------------------");
        System.out.println("OVER!");
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println("揽收：" + finalI);
                    objects1.take();
                    System.out.println("揽收完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }*/

        /*new Thread(() -> {
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 20; i++) {
                try {
//                    TimeUnit.SECONDS.sleep(3);
//                    System.out.println("------before---------");
                    System.out.println(name + "=>" + objects1.take());
//                    System.out.println("---------end---------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3").start();*/
    }
}
