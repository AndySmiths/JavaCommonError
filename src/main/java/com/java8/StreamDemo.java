package com.java8;

import com.demobean.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamDemo {
    public static void main(String[] args) {

//        testRandom();
//        ofTest();
        howStreamIsWork();
    }



    public static void howStreamIsWork(){

        List<String> names = Arrays.asList("张三", "李四", "王老五", "李三", "刘老四", "王小二", "张四", "张五六七123");

        Optional<String> test = names.stream()
                .filter(name -> name.startsWith("张"))
//                .mapToInt(String::length)
                .filter(s -> s.length() > 6)
                .findFirst();
        System.out.println("howStreamIsWork:" + test.isPresent());
    }

    public static void testRandom() {
        IntStream stream = new Random().ints(10, 20, 21);
        IntStream stream1 = new Random().ints();
        stream.forEach(i -> System.out.print(i + " "));
        stream1.forEach(i -> System.out.print(i + " "));

    }

    public void calcStudentHeight() {
        List<Student> studentsList = new ArrayList<>();

        Map<String, List<Student>> stuMap = new HashMap<String, List<Student>>();
        for (Student stu : studentsList) {
            if (stu.getHeight() > 160) { //如果身高大于160
                if (stuMap.get(stu.getSex()) == null) { //该性别还没分类
                    List<Student> list = new ArrayList<Student>(); //新建该性别学生的列表
                    list.add(stu);//将学生放进去列表
                    stuMap.put(stu.getSex(), list);//将列表放到map中
                } else { //该性别分类已存在
                    stuMap.get(stu.getSex()).add(stu);//该性别分类已存在，则直接放进去即可
                }
            }
        }

    }

    public void calcStudentHeightStream() {

        List<Student> stuList = new ArrayList();
        Map<String, List<Student>> stuMap =
                stuList.stream()
                        .filter((Student s) -> s.getHeight() > 160)
                        .collect(Collectors.groupingBy(Student::getSex));


        Map<String, List<Student>> stuMap1 = stuList.parallelStream()
                .filter((Student s) -> s.getHeight() > 160)
                .collect(Collectors.groupingBy(Student ::getSex));
    }

    public static void test1() {
        Object[][] test = new Object[][]{
                {"none", IntStream.empty()},
                {"index 0", IntStream.of(0)},
                {"index 255", IntStream.of(255)},
                {"index 0 and 255", IntStream.of(0, 255)},
                {"index Integer.MAX_VALUE", IntStream.of(Integer.MAX_VALUE)},
                {"index Integer.MAX_VALUE - 1", IntStream.of(Integer.MAX_VALUE - 1)},
                {"index 0 and Integer.MAX_VALUE", IntStream.of(0, Integer.MAX_VALUE)},
                {"every bit", IntStream.range(0, 255)},
                {"step 2", IntStream.range(0, 255).map(f -> f * 2)},
                {"step 3", IntStream.range(0, 255).map(f -> f * 3)},
                {"step 5", IntStream.range(0, 255).map(f -> f * 5)},
                {"step 7", IntStream.range(0, 255).map(f -> f * 7)},
                {"1, 10, 100, 1000", IntStream.of(1, 10, 100, 1000)}/*,
                { "25 fibs", IntStream.generate(new Fibs()).limit(25) }*/
        };

        System.out.println(test.toString());
        //System.out.println(forEachTest(IntStream.range(0, 255)));
    }


    public static void rangeTest() {

    }

    public static void ofTest() {
        forEachTest(IntStream.range(0, 255).map(f -> f * 2));
    }

    public static void forEachTest(IntStream its) {
        its.forEach(i -> System.out.print(i + " "));
    }
}
