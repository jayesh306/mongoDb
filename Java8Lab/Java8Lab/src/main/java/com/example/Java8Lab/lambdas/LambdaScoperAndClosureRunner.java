package com.example.Java8Lab.lambdas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LambdaScoperAndClosureRunner extends BaseRunner {
    private String instanceVariable = "Instance variable";

    @Override
    protected void execute() {
        System.out.println("=== 1. Effectively Final Demo ===");
        effectivelyFinalDemo();

        System.out.println("\n=== 2. Lambda Vs Anonymous Class ===");
        lambdaVsAnonymous();

        System.out.println("\n===3. Closure Demo");
        closureDemo();

        System.out.println(("\n===4. 'this' Keyword Behaviour ==="));
        thisKeywordDemo();
    }

    private void effectivelyFinalDemo(){
        int base = 20;
        Runnable task = () -> {
            System.out.println("Base value : "+base);
        };
        task.run();
        //base = 30;
    }
    private void lambdaVsAnonymous(){
        Runnable anonymous = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous class running");
            }
        };
        Runnable lambda = () -> System.out.println("Lambda class running");
        anonymous.run();
        lambda.run();
    }
    private void closureDemo(){
        int multiplier = 5;
        java.util.function.Function<Integer,Integer> multiply = number -> number * multiplier;
        System.out.println("Closure result : "+multiply.apply(10));
    }
    private void thisKeywordDemo(){
        Runnable lambda = () -> {
            String instanceVariable = "instance variable";
            System.out.println("Lambda this : "+this.instanceVariable);};

        Runnable anonymous = new Runnable() {
            private String instanceVariable = "Anonymous variable";
            @Override
            public void run() {
                System.out.println("Anonymous this "+instanceVariable);
            }
        };
        lambda.run();
        anonymous.run();
    }
}
