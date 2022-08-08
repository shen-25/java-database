package com.zs.lambda.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamSample {

    public  static void generator() {
        String[] arr = {"ally", "andy", "jackson"};
        Stream<String> stream = Stream.of(arr);
        stream.forEach(s-> System.out.println(s));
    }

    //基于集合
    public static void generator1() {
        List<String> list = new ArrayList<>();
        list.add("ally");
        list.add("Andy");
        Stream<String> stream = list.stream();
        stream.forEach(s-> System.out.println(s));
    }

    public static void main(String[] args) {
        generator();
        generator1();
    }
}
