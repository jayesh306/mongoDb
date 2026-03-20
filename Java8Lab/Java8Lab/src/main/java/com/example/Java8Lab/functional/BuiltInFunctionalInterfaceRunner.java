package com.example.Java8Lab.functional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.function.*;

@Component
public class BuiltInFunctionalInterfaceRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        //Predicate
        Predicate<Integer> isEven = n -> n%2==0;
        Predicate<Integer> greaterThanTen = n -> n>10;
        Predicate<Integer> combined = isEven.and(greaterThanTen);
        System.out.println("Is 12 even and >10? "+combined.test(12));

        //Function
        Function<String,Integer> lengthFunction = String::length;
        Function<Integer,Integer> square = n-> n*n;
        System.out.println("Length squared of 'Jayesh' :"+lengthFunction.andThen(square).apply("Jayesh"));

        //Consumer
        Consumer<String> printer = System.out::println;
        printer.accept("Printing using Consumer");

        //Supplier
        Supplier<Double> randomSupplier = Math::random;
        System.out.println("Supplying randomly : "+randomSupplier.get());

        //BiFuction
        BiFunction<Integer,Integer,Integer> add = Integer::sum;
        System.out.println("Addition : "+add.apply(10,20));

        //UnaryOperator
        UnaryOperator<String> toUpper = String::toUpperCase;
        System.out.println("UnaryOperator: "+toUpper.apply("jayesh"));

        //BinaryOperator
        BinaryOperator<Integer> multiply = (a,b) -> a*b;
        System.out.println("Multiplying result : "+multiply.apply(10,2));


    }
}
