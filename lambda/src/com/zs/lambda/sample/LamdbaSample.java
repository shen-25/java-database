package com.zs.lambda.sample;

import com.zs.lambda.MathOperation;

public class LamdbaSample {
    public static void main(String[] args) {
        MathOperation addition = ((a, b) -> a + b + 0f);
        System.out.println(addition.operate(3, 7));
        MathOperation muti = (a, b) -> a * b * 1f;
        System.out.println(muti.operate(10, 100));
        MathOperation sub = (a, b)->{ return a - b + 0f; };
        System.out.println(sub.operate(10, 1));
   }
}
