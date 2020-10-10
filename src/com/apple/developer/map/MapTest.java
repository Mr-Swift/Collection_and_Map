package com.apple.developer.map;
import java.util.*;
public class MapTest {
    public static void main(String[] args) {
        Map<String, Student> staff = new HashMap<>();
        staff.put("001", new Student("张三"));
        staff.put("002", new Student("李四"));
        staff.put("003", new Student("王五"));
        staff.put("004", new Student("刘二麻子"));
        staff.put("005", new Student("狗剩"));
        staff.forEach((k, v) -> System.out.println("key=" + k + ",value=" + v));
    }
}
