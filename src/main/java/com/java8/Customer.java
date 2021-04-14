package com.java8;

import lombok.AllArgsConstructor;
import lombok.Data;

//顾客类
@Data
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;//顾客姓名
}
