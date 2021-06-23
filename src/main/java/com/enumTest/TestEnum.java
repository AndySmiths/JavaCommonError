package com.enumTest;

public class TestEnum {
    public static void main(String[] args) {

        EnumTest enumTest1 = EnumTest.valueOf("SPRING");
        System.out.println(enumTest1);

        EnumTest enumTest = EnumTest.valueOf("1");
        System.out.println(enumTest);
    }
}
