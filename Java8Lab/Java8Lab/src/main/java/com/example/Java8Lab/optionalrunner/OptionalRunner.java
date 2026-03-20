package com.example.Java8Lab.optionalrunner;

import com.example.Java8Lab.BaseRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order(10)
public class OptionalRunner extends BaseRunner {
    @Override
    protected void execute() {
        System.out.println("=== 1. Creating Optional ===");
        createOptional();

        System.out.println("\n=== 2. orElse Vs orElseGet ===");
        orElseVsOrElseGet();

        System.out.println("\n=== 3. map vs flatMap ===");
        mapVsFlatMap();

        System.out.println("\n=== 4. orElseThrow ===");
        orElseThrowExample();
    }

    private void createOptional(){
        Optional<String> optional1 = Optional.of("Jayesh");
        Optional<String> optional2 = Optional.ofNullable(null);
        Optional<String> optional3 = Optional.empty();

        System.out.println(optional1);
        System.out.println(optional2);
        System.out.println(optional3);
    }

    private void orElseVsOrElseGet(){
        Optional<String> optional1 = Optional.of("Jayesh");
        String value1 = optional1.orElse(getDefault());
        String value2 = optional1.orElseGet(this::getDefault);

        System.out.println("orElse result : "+value1);
        System.out.println("orElseGet result : "+value2);
    }
    private String getDefault(){
        System.out.println("Generating Default value.... ");
        return "Default";
    }

    private void mapVsFlatMap(){
        Optional<String> optional =  Optional.of("Jayesh");
        Optional<Integer> length = optional.map(String::length);
        System.out.println("Length using map: "+length);

        Optional<Optional<Integer>> nested = optional.map(name -> Optional.of(name.length()));
        Optional<Integer> flatMap = optional.flatMap(name -> Optional.of(name.length()));

        System.out.println("Nested Optional : "+nested);
        System.out.println("FlatMapped Optional : "+flatMap);
    }

    private void orElseThrowExample(){
        Optional<String> optional = Optional.empty();
        try{
            optional.orElseThrow(() ->
            new IllegalArgumentException("Value not present"));
        }catch (Exception e){
            System.out.println("Exception thrown : "+e.getMessage());
        }
    }
}

/* Optional was introduced to avoid NullPointerException, explicit null checks and for defensive
programming. Its important to understant that Optional is NOT meant to replace all nulls.
Imp -> orElse eagerly evaluates its argument, whereas orElseGet lazily invokes the supplier only
if the Optional is empty.
Q. When should you use flatMap? Ans -> When the mapping function already returns Optional.
Optional<Integer> opt = Optional.empty();
int x = opt.orElse(null); //NPE
bcz unboxing null -> NPE. Its a very common production bug
 */