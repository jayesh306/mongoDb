package com.example.Java8Lab.streams.customcollector;

import com.example.Java8Lab.BaseRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Order(10)
public class CustomCollectorRunner extends BaseRunner {

    @Override
    protected void execute() {
        List<String> names = List.of("Jayesh","Rahul","Amit");
        String result = names.stream().collect(new CustomStringJoinCollector(
                " | ",
                "[",
                "]"
        ));
        System.out.println("Custom Collector Result: "+result);
    }
}
