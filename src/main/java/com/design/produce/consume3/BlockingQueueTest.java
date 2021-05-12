package com.design.produce.consume3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {

    private int maxInventory = 50; // 最大库存

    private BlockingQueue<String> product = new LinkedBlockingQueue<>(maxInventory);//缓存队列

    public void produce(String e) {
        try {
            product.put(e);
            System.out.println("放入一个商品库存，总库存为：" + product.size());
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public String consume(){
        String result = null;
        try {
            result = product.take();
            System.out.println("消费一个商品，总库存为：" + product.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 生产者
     * @author admin
     *
     */
    private class Producer implements Runnable {

        public void run() {
            for (int i = 0; i < 200; i++) {
                produce("商品" + i);
            }
        }

    }

    /**
     * 消费者
     * @author admin
     *
     */
    private class Customer implements Runnable {

        public void run() {
            for (int i = 0; i < 200; i++) {
                consume();
            }
        }
    }

    public static void main(String[] args) {

        BlockingQueueTest lc = new BlockingQueueTest();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();

    }
}
