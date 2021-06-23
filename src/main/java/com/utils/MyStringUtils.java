package com.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MyStringUtils {


    //字符串和集合相互转换
    @Test
    public void test1(){
        String str = "fewfe|f320|fewhi|";
        String[] strArr = str.split("\\|");
        System.out.println("StrArr:" + Arrays.toString(strArr));
        System.out.println("size:" + strArr.length);

        //1. 使用 Collections.addAll 添加, 得到的是一个全新的集合,可以修改操作  推荐
        List<String> stringList = new ArrayList<>();
        Collections.addAll(stringList, strArr);

        //2. 使用Arrays.asList() ,  返回值是java.util.Arrays类中一个私有静态内部类java.util.Arrays.ArrayList，
        //    它并非java.util.ArrayList类。
        List<String> stringList1 = Arrays.asList(strArr);

        // 对每个字符串做修改, 拼接为sql字符串
        String collect = stringList1.stream().map(e -> "'" + e + "'").collect(Collectors.joining(","));
        System.out.printf("拼接结果: %s\n" , collect);
        System.out.println("select * fron wx_cp_userinfo where id in (" + collect + ")");

    }

}
