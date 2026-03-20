package com.example.concurrency.executors.pool.scheduled;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* Its used to schedule tasks to run after a delay or periodically.
 */
@Component
@Profile("scheduled-executor-delayed")
public class ScheduledExecutorDelayedDemo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        System.out.println("Program started, yet to schedule");
        scheduler.schedule(() -> {
            System.out.println("Task executed after delay by "+Thread.currentThread().getName());
        },3, TimeUnit.SECONDS);
        scheduler.shutdown();
    }
}
