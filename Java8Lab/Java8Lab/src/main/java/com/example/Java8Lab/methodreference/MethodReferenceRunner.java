package com.example.Java8Lab.methodreference;

import com.example.Java8Lab.BaseRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferenceRunner extends BaseRunner {
    @Override
    protected void execute() {
        System.out.println("=== 1. Static Method Reference ===");
        staticMethodReference();

        System.out.println("=== 2. Instance Method Reference (Specific Object) ===");
        instanceMethodReference();

        System.out.println("=== 3. Instance Method Reference (Arbitrary Object of Type) ===");
        arbitraryObjectMethodReference();

        System.out.println("=== 4. Constructor Reference ===");
        constructorReference();
    }

    private void staticMethodReference(){
        Function<Integer,Double> sqrt = Math::sqrt;
        System.out.println("Square root of 25 : "+sqrt.apply(25));
    }

    private void instanceMethodReference(){
        String prefix = "Mr. ";
        Function<String,String> addPrefix = prefix::concat;
        System.out.println(addPrefix.apply("Jayesh"));
    }

    private void arbitraryObjectMethodReference(){
        List<String> names = Arrays.asList("jayesh","rahul","amit");
        names.stream().map(String::toUpperCase).forEach(System.out::println);
    }

    private void constructorReference(){
        Supplier<StringBuilder> supplier = StringBuilder::new;
        StringBuilder sb = supplier.get();
        sb.append("Constructor Reference Example");

        System.out.println(sb);
    }
}

// Static Method -> Class::staticMethod -> Integer::sum
// Instance Method(specific object) -> obj::method -> System.out::println
// Instance Method(arbitrary object) -> Class::method -> String::toUpperCase
// Constructor -> Class::new -> ArrayList::new