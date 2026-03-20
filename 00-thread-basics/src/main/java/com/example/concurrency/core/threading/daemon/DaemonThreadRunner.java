package com.example.concurrency.core.threading.daemon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("daemon-task")
public class DaemonThreadRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Thread daemon = new Thread(new DaemonTask(),"log-cleaner-daemon");
        daemon.setDaemon(true);
        daemon.start();
        System.out.println("Main thread completed");
    }
}
