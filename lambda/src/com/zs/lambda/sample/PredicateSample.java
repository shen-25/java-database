package com.zs.lambda.sample;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateSample {
    public static void main(String[] args) {
        Predicate<Integer> predicate = n->n>4;
        //判断一个数字是否符合要求
      //  System.out.println(predicate.test(9));
        List<Integer> list = Arrays.asList(1, 3, 4, 5, 6);
        for (Integer num : list) {

        }
        filter(list, n -> n % 2 == 1);
        System.out.println();
        filter(list, n-> n % 2 == 0);

    }

    public static void filter(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer num : list) {
            if (predicate.test(num)) {
                System.out.print(num + " ");
            }
        }
    }
}
