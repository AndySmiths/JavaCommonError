package com.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MapDemo {

    @Test
    public void test1(){
        Map<String, String> map = new HashMap<>();
        map.put("few", "212");
        map.put("1", "fda");
        map.put("3", "fewfew");
        map.forEach((n, m) -> {
            log.info("{} - {}" , n, m);
        });
    }
}
