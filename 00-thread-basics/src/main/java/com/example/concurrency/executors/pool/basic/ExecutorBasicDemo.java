package com.example.concurrency.executors.pool.basic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* Before java 5, developers used to create threads manually, but problem with that approach is
Thread creation is expensive, Too many threads can crash the system, Not thread resuse, Hard
to manage lifecycle.
Example of a bad system -> 1000 requests = 1000 threads created.
This causes memory exhaustion, context switching overhead, system slowdown.
Solution to this problem is ThreadPool. Instead of creating threads every time
Tasks -> Queue -> Worker threads
Threads are created once and reused.
Ex -> 10 threads handle 10,000 tasks. This is called ThreadPool.
 */
@Component
@Profile("executor-basic")
public class ExecutorBasicDemo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Executor executor = Executors.newSingleThreadExecutor();
        Runnable task = () -> {
            System.out.println("Task executed by : "+Thread.currentThread().getName());
        };
        executor.execute(task);
    }
}
/* Thread created by pool. Not by you.
 */