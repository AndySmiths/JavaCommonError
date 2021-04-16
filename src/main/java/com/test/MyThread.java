package com.test;

public class MyThread extends Thread{

    public MyThread() {
    }

    public MyThread(String name){
        this.setName(name);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName()+":sleep");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
