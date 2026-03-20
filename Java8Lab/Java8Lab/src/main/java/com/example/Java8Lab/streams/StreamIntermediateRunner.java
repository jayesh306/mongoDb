package com.example.Java8Lab.streams;

import com.example.Java8Lab.BaseRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class StreamIntermediateRunner extends BaseRunner {

    @Override
    protected void execute() {
        System.out.println("=== 1. Lazy Evaluation Demo ===");
        lazyEvaluation();

        System.out.println("\n=== 2. Short-Circuiting Demo ===");
        shortCircuitDemo();

        System.out.println("\n=== 3. Infinite Stream using iterate ===");
        infiniteStreamIterate();

        System.out.println("\n=== 4. Infinite Stream using generate ===");
        infiniteStreamGenerate();
    }

    private void lazyEvaluation(){
        Stream.of("Jayesh","Rahul","Amit").filter(name -> {
            System.out.println("Filtering: "+name);
            return name.length() > 4;
        }).map(name -> {
            System.out.println("Mapping: "+name);
            return name.toUpperCase();
        }).findFirst();

        //Intermediate operations are lazy. Execution happends element-by-element, not stage-by-stage
        //Example -> .filter().map().findFirst()
        //Execution flow -> Take first element-> Apply filter -> if passes, apply map -> check findFirst -> stop if found.
    }

    private void shortCircuitDemo(){
        List<String> names = List.of("Jayesh","Rahul","Amit");
        boolean result = names.stream().peek(n-> System.out.println("Checking: "+n))
                .anyMatch(n->n.startsWith("A"));
        System.out.println("Any starts with A? "+result);

        //Short-Circuiting Operations are terminal short-circuiting with methods like findFirst,
        //findAny, anyMatch, allMatch, noneMatch. They stop early when condition is satisfied.
    }

    private void infiniteStreamIterate(){
        Stream.iterate(1, n -> n+1).limit(5).forEach(System.out::println);
    }

    private void infiniteStreamGenerate(){
        Stream.generate(Math::random).limit(4).forEach(System.out::println);
    }
    //Difference between iterate and generate -> Stream.iterate() takes a seed and produces next
    //values from previous. Ex-> Stream.iterate(0,n->n+5).limit(5); Its good for sequences, counters and fibonacci
    //In generate, each value is independent, its good for random values, constant values, repeated generation
}
