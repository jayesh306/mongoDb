package com.example.concurrency.core.threading.creation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("thread-creation")
public class ThreadCreationRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Thread thread1 = new ThreadVsRunnableTask();
        thread1.setName("thread-class");
        thread1.start();

        Thread thread2 = new Thread(new RunnablePreferredTask(),"runnable-task");
        thread2.start();
    }
}
