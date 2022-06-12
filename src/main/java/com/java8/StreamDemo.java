package com.java8;

import com.demobean.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Slf4j
public class StreamDemo {


    @Test
    public void parallelStream(){
        List<Integer> collect = IntStream.generate(() -> 1).limit(100000).boxed().collect(toList());
        List<Integer> list = new ArrayList<>();
        log.info("集合大小：{}", collect.size());
        collect.parallelStream().forEach(list::add);
        log.info("list 的add操作，非线程安全， 结果:{}", list.size());


        log.info("普通foreach循环操作");
        List<Integer> normalList = new ArrayList<>();
        collect.forEach( e -> normalList.add(e + 1));
        log.info("普通foreach循环结果：{}", normalList.size());

        log.info("线程安全集合操作");
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList();
        collect.parallelStream().forEach( e -> {
            copyOnWriteArrayList.add(e);
            if (copyOnWriteArrayList.size() % 1000 == 0) {
                //log.info("当前结果{}， size:{}", e, copyOnWriteArrayList.size());
            }
        });
        log.info("线程安全添加：{}", copyOnWriteArrayList.size());

        log.info("使用包装类 resultList = Collections.synchronizedList(Arrays.asList());");
        List<Integer> streamList = Collections.synchronizedList(new ArrayList<>());
        collect.parallelStream().forEach(streamList::add);
        log.info("包装类结果：{}", streamList.size());

    }


    @Test
    public void parallelStreamTest(){
        Map<String, Integer> resultUserMap = new LinkedHashMap();
        List<String> list = Arrays.asList("1111fewfafew", "2222fewfae", "3333dsaj", "4444ioaf", "5555eioawn",
                "6666jojora", "7777jnaeng", "8888faz");
        list.parallelStream().filter(e -> {
            if (e.contains("a")) {
                resultUserMap.put(e, 1);
            }
            return false;
        }).forEach(emp -> {
            if (emp.contains("c")) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("end!");
        log.info("  >>>> {}", resultUserMap.toString());

    }


    @Test
    public void testFilter(){
        IntStream.range(1, 10)
                .filter(e -> {
                    log.info("e%2:{}", e);
                    if (e%2 == 0) {
                        return true;
                    } else {
                        return false;
                    }
                })
                .forEach(e -> log.info(String.valueOf(e)));
    }


    @Test
    public void streamIterate(){
        // iterate的用法
        String[] arr = {"aaa", "bbb", "ccc", "fewe", "455", "21dfverw", "2", "fdddddd","aaa", "bbb", "ccc", "fewe", "455", "21dfverw", "2", "fdddddd"};
        List<String> stringList = Arrays.asList(arr);

        Stream<Integer> limit = Stream.iterate(0, n -> n * 2).limit(2);
        limit.forEach(integer -> log.info(String.valueOf(integer)));

    }

    @Test
    public void mapTest(){
        // 对给定单词列表 ["Hello","World"],你想返回列表["H","e","l","o","W","r","d"]
        String[] words = new String[]{"Hello","World"};
        List<String[]> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
        a.forEach(System.out::print);
        // .map(word -> word.split("")) 返回的是 Stream(String[]) 而不是 Stream(String)

        log.info("\n--------------");
        // 正确的方式， 再通过flatMap 把两个 Stream(String[]) 转换为 一个 Stream(String) 流
        List<String> a1 = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        a1.forEach(System.out::print);

    }


    //给定单词列表["Hello","World"]，你想要返回列表["H","e","l", "o","W","r","d"]

    @Test
    public void returnHelloWorld(){
        String[] strings = {"Hello", "World"};

        List<String[]> collect = Arrays.asList(strings).stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
        collect.forEach( s -> log.info(Arrays.toString(s)));
//        for (String[] s : collect) {
//            log.info(s.length + Arrays.toString(s));
//        }

    }



    public static void howStreamIsWork(){

        List<String> names = Arrays.asList("张三", "李四", "王老五", "李三", "刘老四", "王小二", "张四", "张五六七123");

        Optional<String> test = names.stream()
                .filter(name -> name.startsWith("张"))
//                .mapToInt(String::length)
                .filter(s -> s.length() > 6)
                .findFirst();
        log.info("howStreamIsWork:" + test.isPresent());
    }

    @Test
    public void testRandom() {
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

        log.info(test.toString());
        //log.info(forEachTest(IntStream.range(0, 255)));
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
