package com.java8;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamDemoTest {

    //通过stream方法把List或数组转换为流
    @Test
    public void stream(){
        Arrays.asList("a1", "a2", "a3").forEach(System.out::println);
        Arrays.stream(new int[]{1, 2, 3}).forEach(System.out::println);
    }

    //
    @Test
    public void of(){
        String[] arr = {"a", "b", "c"};
        Stream.of(arr).forEach(System.out::print);
        Stream.of("a", "b", "c").forEach(System.out::print);
        System.out.println();
        Stream.of(1, 2, "a").forEach(System.out::println);
        System.out.println();
        Stream.of(1, 2, "a").map(item -> item.getClass().getName()).forEach(System.out::println);
    }

    //迭代方式构造无限流，用limit限制个数
    @Test
    public void iterate(){
        Stream.iterate(2, item -> item * 2).limit(10).forEach(System.out::println);
        Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.TEN)).limit(10).forEach(System.out::println);
    }

    //通过外部出入的Supplier 来构造无限流
    @Test
    public void generate() {
        Stream.generate(() -> "test").limit(3).forEach(System.out::println);
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
