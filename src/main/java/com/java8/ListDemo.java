package com.java8;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ListDemo {


    @Test
    public void list2Arr(){

    }

    @Test
    public void arr2List(){

        String[] arr = {"aaa", "bbb", "ccc"};

        System.out.println(Arrays.toString(arr));

        System.out.println("---------- 此种方法转换的list是Arrays的内部类， 固定长度，不能删除、添加等操作----------");
        List<String> stringList = Arrays.asList(arr);

        System.out.println(stringList.toString());

        // java.lang.UnsupportedOperationException
        // stringList.add("fewfe");


        System.out.println("---------- 数据拷贝的方式转换为可增加删除集合----------");
        ArrayList<String> strings = new ArrayList<>(stringList);
        strings.add("fewfe");
        System.out.println(strings.toString());

        System.out.println("----------流的方式转换为可增加删除集合----------");
        List<String> collect = Arrays.stream(arr).collect(Collectors.toList());
        collect.add("test");
        System.out.println(collect.toString());

    }

    @Test
    public void listPartition(){

        String[] arr = {"aaa", "bbb", "ccc", "fewe", "455", "21dfverw", "2", "fdddddd","aaa", "bbb", "ccc", "fewe", "455", "21dfverw", "2", "fdddddd"};
        List<String> stringList = Arrays.asList(arr);

        int groupSize = 3;

        log.info("------------方法一 第三方库实现-------------");
        List<List<String>> partition = ListUtils.partition(stringList, groupSize);
        AtomicInteger index = new AtomicInteger();
        partition.stream().forEach( part -> {
            log.info("  分组 {}, : {}", index.getAndIncrement(), part.toString());
        });


        log.info("------------方法二 java 8 stream 遍历操作---------------");
        int count = (stringList.size() +  groupSize - 1) / groupSize;
        log.info("计算切分次数: {}", count);

        List<List<String>> mgList = new ArrayList<>();
        Stream.iterate(0, n -> n + 1).limit(count).forEach( i -> {
            List<String> collect = stringList
                    .stream()
                    .skip(i * groupSize)
                    .limit(groupSize)
                    .collect(Collectors.toList());
            log.info("  分组 {} : {}" , i, collect.toString());
            mgList.add(collect);
        });

        log.info("-----------方法三 获取分组后的集合 ---------------");

        List<List<String>> splitList = Stream.iterate(0, n -> n + 1)
                .limit(count).parallel()
                .map(a -> stringList.stream().skip(a * groupSize).limit(groupSize).parallel().collect(Collectors.toList()))
                .collect(Collectors.toList());
        log.info("" , splitList.toString());
    }

    @Test
    public void printList(){
        String[] arr = {"aaa", "bbb", "ccc"};
        System.out.println("数组输出:" + Arrays.toString(arr));

        List<String> stringList = Arrays.asList(arr);
        System.out.println( "list 集合输出:" + stringList.toString());

    }










}
