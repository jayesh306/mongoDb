package com.example.concurrency.core.threading.contextswitch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("context-swicth-runner")
public class ContextSwitchRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Runnable task = new CPUIntensiveTask();
        Thread t1 = new Thread(task,"cpu-thread-1");
        Thread t2 = new Thread(task,"cpu-thread-2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
