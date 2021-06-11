package io.viren.java8designpatterns.chaining;

import io.viren.java8designpatterns.util.Consumer;

public class ConsumersChainingDemo {

    public static void main(String[] args) {

        Consumer<String> consumer1 = item -> System.out.println("Calling Consumer 1: " + item);
        Consumer<String> consumer2 = item -> System.out.println("Calling Consumer 2: " + item);

        // without chaining , if need to spawn another consumer out of above twos,
        Consumer<String> chainedOne = item -> {
            consumer1.accept(item);
            consumer2.accept(item);
        };
        chainedOne.accept("For us");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // with chained operator
        consumer1.andThen(consumer2).accept("For us");

    }
}
