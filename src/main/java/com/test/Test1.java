package com.test;

import java.util.concurrent.*;

public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        Executor executor= Executors.newCachedThreadPool();

        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(1,Integer.MAX_VALUE,1, TimeUnit.SECONDS,new SynchronousQueue<>());
        for(int i=0;i<10;i++){
            String name = "liudami";
            MyThread t1;
            Thread t2;
            Thread.sleep(500);
            System.out.println("threadPoolExecutor.getPoolSize()："+threadPoolExecutor.getPoolSize());
            System.out.println("i:" + i);
            if (i < 3) {
                t1 = new MyThread(name);
                threadPoolExecutor.execute(t1);
            }else {
                t1 = new MyThread();
                threadPoolExecutor.execute(t1);
            }

        }
    }
}
