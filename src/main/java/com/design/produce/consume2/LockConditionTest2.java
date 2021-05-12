package com.design.produce.consume2;


import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock控制线程并行执行， condition控制消费者线程状态
 */
public class LockConditionTest2 {

    private LinkedList<String> product = new LinkedList<>();

    private AtomicInteger inventory = new AtomicInteger(0);//实时库存

    private Lock consumerLock = new ReentrantLock();// 资源锁

    private Lock productLock = new ReentrantLock();// 资源锁

    private Condition notEmptyCondition = consumerLock.newCondition();

    private Condition notFullCondition = productLock.newCondition();

    private int maxInventory = 20;

    public void produce(String e) {
        productLock.lock();
        try {
            while (inventory.get() == maxInventory) {
                notFullCondition.await();
            }
            product.add(e);
            System.out.println("放入一个商品库存，总库存为：" + inventory.incrementAndGet());
            if (inventory.get() < maxInventory) {
                notFullCondition.signalAll();
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } finally {
            productLock.unlock();
        }

        if (inventory.get() > 0) {
            try {
                consumerLock.lockInterruptibly();
                notEmptyCondition.signalAll();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } finally {
                consumerLock.unlock();
            }
        }
    }

    public void consume(){
        consumerLock.lock();
        try {
            while (inventory.get() == 0) {
                notEmptyCondition.await();
            }
            product.removeLast();
            System.out.println("消费一个商品，总库存为：" + inventory.decrementAndGet());
            if (inventory.get() > 0) {
                notEmptyCondition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consumerLock.unlock();
        }

        if (inventory.get() < maxInventory) {
            try {
                productLock.lockInterruptibly();
                notFullCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                productLock.unlock();
            }
        }
    }

    private class Produce implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                produce("商品：" + i);
            }
        }
    }

    private class Consume implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                consume();
            }
        }
    }
    public static void main(String[] args) {
        LockConditionTest2 lc2 = new LockConditionTest2();
        new Thread(lc2.new Produce()).start();
        new Thread(lc2.new Consume()).start();
    }
}
