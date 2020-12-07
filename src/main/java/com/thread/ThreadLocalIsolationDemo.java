package com.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal
 * 线程隔离
 * 问题：ThreadLocal 适用于变量在线程间隔离，而在方法或类间共享的场景。
 * 如果用户信息的获取比较昂贵（比如从数据库查询用户信息），那么在 ThreadLocal 中缓存数据是比较合适的做法。
 * 但，这么做为什么会出现用户信息错乱的 Bug 呢？
 * <p>
 * 顾名思义，线程池会重用固定的几个线程，
 * 一旦线程重用，那么很可能首次从 ThreadLocal 获取的值是之前其他用户的请求遗留的值。
 * 这时，ThreadLocal 中的用户信息就是其他用户的信息。
 */
@SpringBootApplication
@Slf4j
@RestController
public class ThreadLocalIsolationDemo {

    private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);

    public static void main(String[] args) {
        log.info("Start main");
        SpringApplication.run(ThreadLocalIsolationDemo.class, args);
    }

    @GetMapping("wrong")
    public Map wrong(@RequestParam("userId") Integer userId) {
        log.info("Start wrong");
        //设置用户信息之前先查询一次ThreadLocal中的用户信息
        String before = Thread.currentThread().getName() + ":" + currentUser.get();
        //设置当前的用户id到threadlocal中
        currentUser.set(userId);

        //设置用户信息之后再查询一次ThreadLocal中的用户信息
        String after = Thread.currentThread().getName() + ":" + currentUser.get();

        Map result = new HashMap();
        result.put("before", before);
        result.put("after", after);
        return result;
    }

    @GetMapping("right")
    public Map right(@RequestParam("userId") Integer userId) {
        log.info("Start wrong");
        //设置用户信息之前先查询一次ThreadLocal中的用户信息
        String before = Thread.currentThread().getName() + ":" + currentUser.get();
        //设置当前的用户id到threadlocal中
        currentUser.set(userId);

        try {
            //设置用户信息之后再查询一次ThreadLocal中的用户信息
            String after = Thread.currentThread().getName() + ":" + currentUser.get();

            Map result = new HashMap();
            result.put("before", before);
            result.put("after", after);
            return result;
        } finally {
            currentUser.remove();
        }
    }
}
