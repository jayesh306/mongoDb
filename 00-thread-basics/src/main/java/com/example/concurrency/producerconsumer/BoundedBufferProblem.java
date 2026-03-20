package com.example.concurrency.producerconsumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/* Fixed size buffer, Backpressure mechanism, Prevent memory explosion.
This is exactly what : ThreadPoolExecutor uses, Kafka does, Reactive streams use
Producer-Consumer is used in (real world Spring-boot project) : @Async task queues,
ExecutorService, Web request processing, Kafka message processing, Background job systems.
 */
@Component
@Profile("bounded-buffer-problem")
public class BoundedBufferProblem implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);
        Runnable producer = () -> {
            int value = 0;
            while(true){
                try{
                    buffer.put(value);
                    System.out.println(Thread.currentThread().getName()+" produced: "+value);
                    value++;
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        };
        Runnable consumer = () -> {
            while (true){
                try{
                    int take = buffer.take();
                    System.out.println(Thread.currentThread().getName()+" consumed : "+take);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
