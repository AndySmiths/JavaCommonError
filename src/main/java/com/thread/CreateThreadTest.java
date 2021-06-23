package com.thread;

import ch.qos.logback.core.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.stream.Stream;

@Slf4j
public class CreateThreadTest {

    /**
     * 通过线程池创建线程
     */
    @Test
    public void test3(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Stream.iterate(0, n -> n + 1).limit(100).forEach( e -> executorService.execute(new Thread(){
            @Override
            public void run() {
                log.info("线程在运行 {}", this.getName());
                super.run();
            }
        }));

    }

    @Test
    public void test2(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        threadPoolExecutor.execute(new Thread(){
            @Override
            public void run() {
                log.info("线程在运行 {}", this.getName());
                super.run();
            }
        });
    }

    /**
     * 基本方法创建线程
     */
    @Test
    public void test1(){
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    log.info("线程在运行 {}", this.getName());
                    super.run();
                }
            }.start();
        }
    }
}
