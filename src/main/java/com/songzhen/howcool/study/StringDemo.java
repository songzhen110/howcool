package com.songzhen.howcool.study;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StringDemo implements InstantiationAwareBeanPostProcessor, BeanPostProcessor {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String a = "abc";
        Field value = a.getClass().getDeclaredField("value");
        value.setAccessible(true);
        value.set(a,"abcd".toCharArray());


        System.out.println("args = " + a);

       moda(a);
       modb(a);
       System.out.println(a);

    }

    public static String moda(String a){
        a=a+"a";
        return a;
    }
    public static String modb(String a){
        a=a+"b";
        return a;
    }
}
