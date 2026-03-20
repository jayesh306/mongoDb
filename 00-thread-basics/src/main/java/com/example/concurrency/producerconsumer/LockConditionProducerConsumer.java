package com.example.concurrency.producerconsumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/* This is modern replacement of wait/notify

 */

@Component
@Profile("lock-condition-producer-consumer")
public class LockConditionProducerConsumer implements CommandLineRunner {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int CAPACITY = 5;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    @Override
    public void run(String... args) throws Exception {
        Thread producer = new Thread(() -> {
            int val =0;
            while(true){
                lock.lock();
                try {
                    while(buffer.size() == CAPACITY){
                        notFull.await();
                    }
                    System.out.println("Produced: "+val);
                    val++;
                    Thread.sleep(1000);
                    notEmpty.signal();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){}
            }
        });

        Thread consumer = new Thread(() -> {
            while(true){
                lock.lock();
                try {
                    while (buffer.size() == CAPACITY) {
                        notEmpty.await();
                    }
                    int val = buffer.poll();
                    System.out.println("Consumer : "+val);
                    notFull.signal();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }finally {
                    lock.unlock();
                }
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        });
        producer.start();
        consumer.start();
    }
}
