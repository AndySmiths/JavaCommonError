package com.com.java8;

import java.util.Random;
import java.util.stream.IntStream;

public class StreamDemo {
    public static void main(String[] args) {

//        testRandom();
        ofTest();
    }

    public static void testRandom(){
        IntStream stream = new Random().ints(10, 20, 21);
        IntStream stream1 = new Random().ints();
        stream.forEach(i -> System.out.print(i + " "));
        stream1.forEach(i -> System.out.print(i + " "));

    }

    public static void test1(){
        Object[][] test = new Object[][] {
                { "none", IntStream.empty() },
                { "index 0", IntStream.of(0) },
                { "index 255", IntStream.of(255) },
                { "index 0 and 255", IntStream.of(0, 255) },
                { "index Integer.MAX_VALUE", IntStream.of(Integer.MAX_VALUE) },
                { "index Integer.MAX_VALUE - 1", IntStream.of(Integer.MAX_VALUE - 1) },
                { "index 0 and Integer.MAX_VALUE", IntStream.of(0, Integer.MAX_VALUE) },
                { "every bit", IntStream.range(0, 255) },
                { "step 2", IntStream.range(0, 255).map(f -> f * 2) },
                { "step 3", IntStream.range(0, 255).map(f -> f * 3) },
                { "step 5", IntStream.range(0, 255).map(f -> f * 5) },
                { "step 7", IntStream.range(0, 255).map(f -> f * 7) },
                { "1, 10, 100, 1000", IntStream.of(1, 10, 100, 1000) }/*,
                { "25 fibs", IntStream.generate(new Fibs()).limit(25) }*/
        };

        System.out.println(test.toString());
        //System.out.println(forEachTest(IntStream.range(0, 255)));
    }


    public static void rangeTest(){

    }

    public static void ofTest(){
        forEachTest(IntStream.range(0, 255).map(f -> f * 2));
    }

    public static void forEachTest(IntStream its){
        its.forEach(i -> System.out.print(i + " "));
    }
}
