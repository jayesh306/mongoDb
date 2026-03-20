package com.example.Java8Lab.streams;

import com.example.Java8Lab.BaseRunner;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
public class StreamTerminalRunner extends BaseRunner {
    @Override
    protected void execute() {

        List<Integer> numbers = List.of(5,10,15,20);
        System.out.println("=== forEach ===");
        numbers.stream().forEach(System.out::println);

        System.out.println("\n=== count ===");
        long count = numbers.stream().count();
        System.out.println("Count : "+count);

        System.out.println("\n=== min/max ===");
        Optional<Integer> min = numbers.stream().min(Integer::compare);
        Optional<Integer> max = numbers.stream().max(Integer::compareTo);
        System.out.println("Min : "+min.orElse(null));
        System.out.println("Max : "+max.orElse(null));

        System.out.println("\n=== anyMatch / allMatch / noneMatch ===");
        System.out.println("Any > 18 ? "+numbers.stream().anyMatch(n->n>18));

        System.out.println("All > 2? "+numbers.stream().allMatch(n-> n>2));

        System.out.println("None < 0? "+numbers.stream().noneMatch(n->n<0));

        System.out.println("\n=== findFirst() Vs findAny() ===");
        System.out.println("findAny: "+numbers.parallelStream().findAny().orElse(null));
        System.out.println("findFirst: "+numbers.parallelStream().findFirst().orElse(null));

        System.out.println("\n=== reduce with identity ===");
        int sum = numbers.stream().reduce(0,Integer::sum);
        System.out.println(sum);

        System.out.println("\n=== reduce without identity ===");
        Optional<Integer> result = numbers.stream().reduce((a,b) -> a+b);
        System.out.println("result : "+result.orElse(null));

    }
}
