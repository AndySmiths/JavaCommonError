package com.java8;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class FunctionDemo {


    /**
     *  如果 字符串为空，显示 "字符串不能为空"，如果字符串长度大于3，显示 "字符串过长"。那么按照普通的方式，我就就是两个 if 语句。
     */

    @Test
    public void demo1(){
        String name = "";
        String name1 = "12345";
        System.out.println(validInput(name, str -> str.isEmpty() ? "名字不能为空" : str));
        System.out.println(validInput(name1, str -> str.length() > 3 ? "名字过长" : str));
    }

    public static String validInput(String name, Function<String,String> function) {
        return function.apply(name);
    }
}
