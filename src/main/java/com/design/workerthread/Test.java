package com.design.workerthread;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 场景 : 通过 Worker Thread 设计模式来完成一个物流分拣的作业。
 *
 * 假设一个物流仓库的物流分拣流水线上有 8 个机器人，
 * 它们不断从流水线上获取包裹并对其进行包装，送其上车。
 * 当仓库中的商品被打包好后，会投放到物流分拣流水线上，
 * 而不是直接交给机器人，机器人会再从流水线中随机分拣包裹。
 */
public class Test {

    public static void main(String[] args) {

        final PackageChannel channel = new PackageChannel(8);

        channel.startWorker();

        //为流水线添加包裹


        for(int i=0; i<100; i++) {
            Package packagereq = new Package();
            packagereq.setAddress("test-" + i);
            packagereq.setName("test-" + i);
            channel.put(packagereq);
        }

//        Stream.generate( i -> new Package()).limit( 100).forEach(System.out::println);
    }
}
