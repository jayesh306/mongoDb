package com.example.concurrency.locks.and.synchronizers.synchronizers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.CyclicBarrier;

/* All threads wait for each other at a barrier point.
Barrier = reusable synchronization point.
It can be used for multi-stage data processing, Parallel computation(like matrix multiplication),
Gaming engines, Simulation systems
 */
@Component
@Profile("cyclic-barrier")
public class CyclicBarrierDemo implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        int parties =3;
        CyclicBarrier barrier = new CyclicBarrier(parties, () -> System.out.println(
                "All threads reached barrier. Processing together."
        ));
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName()+" performing task");
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName()+" waiting at barrier.");
            try{
                barrier.await();
            }catch (Exception e){}
         //   System.out.println("Completed all tasks");
        };
        for(int i=0;i<parties;i++){
            new Thread(task,"Thread-"+i).start();
        }
    }
}
