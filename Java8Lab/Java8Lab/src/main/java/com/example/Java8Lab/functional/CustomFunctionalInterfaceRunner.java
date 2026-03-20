package com.example.Java8Lab.functional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomFunctionalInterfaceRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        PriceCalculator withTax = price -> price*1.18;
        PriceCalculator discount = price -> price-10 ;

        System.out.println("With tax: "+withTax.calculate(100));
        System.out.println("After discount: "+discount.calculate(100));
    }
}
