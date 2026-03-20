package com.example.concurrency.producerconsumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* Executor Framework uses BlockingQueue internally.
This is better bcz there is no manual synchronization, No spurious wakeup handling, Its more
cleaner, and its used in ThreadPoolExecutor, Kafka Consumers, Logging frameworks.
 */
@Component
@Profile("blocking-queue-test")
public class BlockingQueueProducerConsumer implements CommandLineRunner {
    private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    @Override
    public void run(String... args) throws Exception {
        Thread producer = new Thread(() -> {
            int val=0;
            while(true){
                try {
                    queue.put(val);
                    System.out.println("Producer: " + val);
                    val++;
                    Thread.sleep(1000);
                }catch (InterruptedException e){}
            }
        });
        Thread consumer = new Thread(() -> {
            while(true){
                try {
                    int val = queue.take();
                    System.out.println("Consumer : "+val);
                    Thread.sleep(1000);
                }catch (InterruptedException e){}
            }
        });
        producer.start();
        consumer.start();

        new Thread(() ->{
            try {
                Thread.sleep(5000);
                producer.interrupt();
                consumer.interrupt();
            }catch (InterruptedException e){}
        }).start();
    }
}
