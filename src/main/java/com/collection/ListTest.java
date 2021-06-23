package com.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

    @Test
    public void list2String(){
        List<String> list = new ArrayList<>();
        list.add("fwe");
        list.add("joi");
        list.add("9032903");
        System.out.println(list.toString());
    }


}
