package com.java8;

public class AnonymousClass {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hello1");
            }
        }).start();

        new Thread(() -> System.out.println("hello2")).start();
    }
}
