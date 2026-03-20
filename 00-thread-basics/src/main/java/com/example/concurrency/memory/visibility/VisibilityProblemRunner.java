package com.example.concurrency.memory.visibility;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("visibility-proble-runner")
public class VisibilityProblemRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        FlagHolder holder = new FlagHolder();
        Thread worker = new Thread(() -> {
            System.out.println("Started holder thread.");
            while(holder.isRunning()){
                //busy loop
            }
            System.out.println("Stopped.");
        });
        worker.start();
        Thread.sleep(1000);
        holder.stop();
        System.out.println("Flag updated to false");
    }

}
