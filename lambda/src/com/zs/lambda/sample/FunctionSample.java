package com.zs.lambda.sample;

import java.util.Random;
import java.util.function.Function;

public class FunctionSample {
    public static void main(String[] args) {
        Function<Integer, String> randomStringFunction = len->{
            String str = "abcdefghijklmnopqrst0123456789";
            StringBuffer stringBuffer = new StringBuffer();
            Random random = new Random();
            for (int i = 0; i < len; i++) {
                int position = random.nextInt(str.length());
                stringBuffer.append(str.charAt(position));
            }
            return stringBuffer.toString();
        };
        String str = randomStringFunction.apply(10);
        System.out.println(str);
    }
}
