package com.song;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// jndi 注入
public class Test {
    private static final Logger logger = LogManager.getLogger(Test.class);
    public static void main(String[] args) {

        String user1 = "zhangshan";
        String user2 = "${java:os}";
        String user3 = "${java:vm}";
        String user4 = "${java:version}";
        String user5 = "${java:runtime}";
        String user6 = "${java:locale}";
        String user7 = "${java:hw}";

        logger.info("name:{}\r\n{}\n{} \n{} \n{}\n{}\n{}\n", user1, user2, user3, user4, user5, user6, user7);
    }
}
