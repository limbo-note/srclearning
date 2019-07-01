package com.limbo.netty;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ServerMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // context.start();
        HashMap<String, String> map = new HashMap<>();
        map.put(null, "1");
        System.out.println(map.get(null));
        map.remove(null);
        System.out.println(map.get(null));
    }
}
