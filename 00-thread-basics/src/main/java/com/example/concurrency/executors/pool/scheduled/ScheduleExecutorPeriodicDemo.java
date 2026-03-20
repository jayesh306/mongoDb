package com.example.concurrency.executors.pool.scheduled;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@Profile("scheduled-executor-periodic")
public class ScheduleExecutorPeriodicDemo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Running periodic task : "+Thread.currentThread().getName());
        },0,3, TimeUnit.SECONDS);
       // scheduler.shutdown();
    }
}
