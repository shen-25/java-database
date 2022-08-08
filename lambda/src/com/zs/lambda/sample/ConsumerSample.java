package com.zs.lambda.sample;

import java.util.function.Consumer;

public class ConsumerSample {
      public  static void output(Consumer<String> consumer){
        String text = "我不知道啊";
        consumer.accept(text);
    }
    public static void main(String[] args) {
        output(s -> System.out.println("向控制台输出：" + s));
        output(s->{
            System.out.println("向网站输出：" + s);
        });
    }
}
