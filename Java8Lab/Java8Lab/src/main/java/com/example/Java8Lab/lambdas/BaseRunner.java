package com.example.Java8Lab.lambdas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        printHeader();
        execute();
        printFooter();
    }
    protected abstract void execute();

    private void printHeader(){
        System.out.println("====================================");
        System.out.println("Running "+this.getClass().getSimpleName());
        System.out.println("=====================================");
    }
    private void printFooter(){
        System.out.println("|||||||||||Footer|||||||||||||");
    }
}
