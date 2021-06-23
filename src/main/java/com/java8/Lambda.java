package com.java8;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda {
    public static void main(String[] args) {
        foreachTest();
        System.out.println("----------------");
        foreachTest2();
    }

    public static void foreachTest(){

        int i = 0;
        // 创建一个集合来保存自变量数据
        List<Integer> list = new ArrayList();
        list.add(++i);
        list.add(++i);
        list.add(++i);
        list.add(++i);
        // jdk 8 遍历
        list.forEach(e -> System.out.println(e));
        list.forEach(System.out::println);

        list.add(++i);

        System.out.println(list.toString());

    }

    public static void foreachTest2(){

        int i = 0;

        List<Integer> list = new ArrayList<>();
        list.add(++i);
        list.add(++i);
        list.add(++i);
        list.add(++i);
        list.forEach(cc -> System.out.println(cc));
        list.forEach(System.err :: println);
        list.add(++i);
        Integer[] ints = new Integer[]{};
        ints = list.toArray(ints);
        System.out.println(Arrays.asList(ints));
    }

    public void supplierDemo(){
        //使用lambda表达式提供supplier接口实现，返回OK字符串
        Supplier<String> stringSupplier = () -> "OK";
        //使用方法引用提供supplier接口实现，返回空字符串
        Supplier<String> supplier = String::new;
    }

    //Predicate 接口是输入一个参数，返回boolean值。我们通过and方法组合两个Predicate条件，判断值是否大于0并且是偶数
    public void predicateDemo(){
        Predicate<Integer> positiveNumber = i -> i  > 0;
        Predicate<Integer> evenNumber = i -> i % 2 == 0;
        Assert.isTrue((positiveNumber.and(evenNumber).test(2)), "");
    }

}
