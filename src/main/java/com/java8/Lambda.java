package com.java8;

import org.springframework.util.Assert;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda {
    public static void main(String[] args) {

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
