package com.example.Java8Lab.functional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class FunctionCompositionRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Function<String,String> trim = String::trim;
        Function<String,String> toUpper = String::toUpperCase;
        Function<String,String> addPrefix = s-> "Mr. "+s;

        Function<String,String> pipeline = trim.andThen(toUpper).andThen(addPrefix);
        System.out.println(pipeline.apply("   jayesh   "));
    }
}

//Its a function that takes a function as parameter or returns a function like :
//public static <T> Function<T,T> repeat(Function<T,T> f){
//    return f.andThen(f);
//}
