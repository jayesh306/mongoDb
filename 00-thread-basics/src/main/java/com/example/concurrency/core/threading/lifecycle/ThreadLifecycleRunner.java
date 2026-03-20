package com.example.concurrency.core.threading.lifecycle;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("thread-states")
public class ThreadLifecycleRunner implements CommandLineRunner {

    private static final Object LOCK = new Object();

    @Override
    public void run(String... args) throws Exception {
        Thread worker = new Thread(() ->{
            try{
                ThreadStateLogger.log("Entered run()",Thread.currentThread());
                synchronized (LOCK){
                    ThreadStateLogger.log("Worker acquired LOCK (after being Blocked)",Thread.currentThread());
                    Thread.sleep(2000);  //TIMED_WAITING
                }
                //Now WAITING (releases lock)
                synchronized(LOCK){
                    ThreadStateLogger.log("Worker calling wait()",Thread.currentThread());
                    LOCK.wait();   //WAITING
                    ThreadStateLogger.log("Worker resumed after notify()",Thread.currentThread());
                }
                ThreadStateLogger.log("Worker about to finish",Thread.currentThread());
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        },"order-worker-1");

        //NEW
        ThreadStateLogger.log("After creation",worker);
        worker.start();
        Thread.sleep(100);
        ThreadStateLogger.log("After start()",worker);

        synchronized (LOCK){
            Thread.sleep(100);
            ThreadStateLogger.log("Main holding LOCK",worker);
        }

        Thread.sleep(3000);
        ThreadStateLogger.log("Before notify()",worker);

        //Blocked(main holds LOCK, worker tried to enter)
        synchronized(LOCK){
            Thread.sleep(100);
            ThreadStateLogger.log("Main holding LOCK",worker);
        }

        //Notify waiting thread
        synchronized (LOCK){
            LOCK.notify();
        }
        Thread.sleep(100);
        ThreadStateLogger.log("After notify()",worker);
    }
}
