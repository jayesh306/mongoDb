package com.example.concurrency.memory.visibility.volatilefix;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("volatile-runner")
public class VolatileFixRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        VolatileFlagHolder holder = new VolatileFlagHolder();
        Thread worker = new Thread(() -> {
            System.out.println("Started VolatileFixRunner");
            while(holder.isRunning()){
                //loop
            }
            System.out.println("Stopped with volatile");
        });
        worker.start();
        Thread.sleep(1000);
        holder.stop();
    }
}
