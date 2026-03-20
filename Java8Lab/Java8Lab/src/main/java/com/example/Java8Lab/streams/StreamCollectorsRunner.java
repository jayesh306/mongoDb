package com.example.Java8Lab.streams;

import com.example.Java8Lab.BaseRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Order(1)
public class StreamCollectorsRunner extends BaseRunner {
    @Override
    protected void execute() {
        List<String> names = List.of("Jayesh","Rahul","Amit","Sneha","Priya");
        List<String> upper = names.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(upper);
        Map<String,Integer> nameLengthMap = names.stream().collect(Collectors.toMap(name->name,String::length));
        System.out.println(nameLengthMap);

        System.out.println("\n=== groupingBy (length) ===");
        Map<Integer,List<String>> groupedByLength = names.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(groupedByLength);

        System.out.println("\n=== partitioningBy ===");
        Map<Boolean, List<String>> partitioned = names.stream().collect(Collectors.partitioningBy(name -> name.length()>5));
        System.out.println(partitioned);

        System.out.println("\n=== counting (downstream) ===");
        Map<Integer, Long> countByLength = names.stream().collect(Collectors.groupingBy(String::length,Collectors.counting()));
        System.out.println(countByLength);

        System.out.println("\n=== mapping (downstream) ===");
        Map<Integer, List<String>> mappedUpper = names.stream().collect(Collectors.groupingBy(String::length,Collectors.mapping(String::toUpperCase,Collectors.toList())));
        System.out.println(mappedUpper);

        System.out.println("\n=== summarizingInt ===");
        var stats = names.stream().collect(Collectors.summarizingInt(String::length));
        System.out.println(stats);

        System.out.println("\n=== joining ===");
        String joined = names.stream().collect(Collectors.joining(", "));
        System.out.println(joined);

        System.out.println("\n=== collectingAndThen ===");
        List<String> unmodifiable = names.stream().collect(Collectors.collectingAndThen(Collectors.toList(),List::copyOf));
        System.out.println(unmodifiable);

        List<Integer> numbers = List.of(1,2,3,4)
;        System.out.println("\n=== Non-associative Example (Danger) ===");
        int wrong = numbers.parallelStream().reduce(0,(a,b) -> a-b);
        System.out.println("Parallel subtraction result : "+wrong);

        System.out.println("\n=== 3-Argument Reduce ===");
        int multiplied = numbers.parallelStream().reduce(1,(a,b) -> a*b,
                (x,y)->x*y);
        System.out.println("Product using 3-arg reduce: "+multiplied);
    }
}

/*
collect vs reduce -> reduces is immutable reduction, and it combines values.
collect is a mutable reduction and its more efficient for collections and it uses collector interface.
reduce is meant for immutable accumulation, whereas collect is optimized for mutable reduction
such as building lists or maps.*/

/* Reduce is a terminal operation that combines stream elements into a single result using an
associative accumulation function.
In parallel stream [1,2,3,4] -> split into [1,2] and [3,4] then reduces separately (1-2) and (3-4)
then combines -> result1 - result2 -> so different order brings different results.
Reduce is not good for mutable objects as it breaks parallel safety. The correct approach is to
always use collect().
In parallel stream, identity is applied to each partition. Combiner merges partial results.
if identity is not neutral then final result is incorrect.
For ex-> .reduce(10, Integer::sum); Parallel execution may add 10 multiple times.
 */