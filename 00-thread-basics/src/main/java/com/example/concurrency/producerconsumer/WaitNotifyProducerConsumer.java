package com.example.concurrency.producerconsumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@Profile("wait-notify-consumer-producer")
public class WaitNotifyProducerConsumer implements CommandLineRunner {
    private final List<Integer> buffer = new LinkedList<>();
    private final int CAPACITY = 5;

    @Override
    public void run(String... args) throws Exception {
        Thread producer = new Thread(() -> {
            int value=0;
            while(true) {
                synchronized (buffer) {
                    while (buffer.size() == CAPACITY) {
                        System.out.println("Entered producer while loop.");
                        waitSafely();
                    }
                    buffer.add(value);
                    System.out.println("Produced: " + value);
                    value++;
                    buffer.notifyAll();
                }
                sleep(1000);
            }
        });
        Thread consumer = new Thread(() -> {
            while (true){
                synchronized (buffer){
                    while (buffer.isEmpty()){
                        waitSafely();
                    }
                    int val = buffer.remove(0);
                    System.out.println("Consumed: "+val);
                    buffer.notifyAll();
                }
                sleep(1000);
            }
        });
        producer.start();
        consumer.start();
    }
    private void waitSafely(){
        try{
            buffer.wait();
        }catch (InterruptedException ignore){}
    }
    private void sleep(long ms){
        try{
            Thread.sleep(ms);
        }catch (InterruptedException e){}
    }
}
