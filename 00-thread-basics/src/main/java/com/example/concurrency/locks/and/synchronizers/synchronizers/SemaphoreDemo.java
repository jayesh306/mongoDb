package com.example.concurrency.locks.and.synchronizers.synchronizers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.Semaphore;

/* It controls the number of threads accessing a resource.
Example : Think about limited parking slots.
Real world use cases:
Database connection pool, API rate limiting, Limiting concurrency file uploads, restricting
access to critical external service.

 */
@Component
@Profile("semaphore-test")
public class SemaphoreDemo implements CommandLineRunner {
    private final Semaphore semaphore = new Semaphore(2); //2 permits
    @Override
    public void run(String... args) throws Exception {
        Runnable task = () -> {
            try{
                //only 2 permits allowed here
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+" acquired.");
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){}
                System.out.println(Thread.currentThread().getName()+" release permits.");
            }catch (InterruptedException e){

            }finally {
                semaphore.release();
            }
        };
        for(int i=0;i<=5;i++){
            new Thread(task,"Thread-"+i).start();
        }
    }
}
