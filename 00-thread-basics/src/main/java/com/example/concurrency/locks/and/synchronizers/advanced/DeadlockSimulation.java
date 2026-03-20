package com.example.concurrency.locks.and.synchronizers.advanced;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("deadlock-simulation")
public class DeadlockSimulation implements CommandLineRunner {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();


    @Override
    public void run(String... args) throws Exception {
        Thread t1 = new Thread(() -> {
            synchronized (lock1){
                System.out.println("T1 acquired lock1");
                sleep();
                synchronized (lock2){
                    System.out.println("T1 acquired lock2");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (lock2){
                System.out.println("T2 acquired lock2");
                sleep();
                synchronized (lock1){
                    System.out.println("T2 acquired lock1");
                }
            }
        });
        t1.start();
        t2.start();

    }
    public void sleep(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
/* 4 conditions of Deadlock : 1. Mutual Exclusion : At least one resource must be non-shareable
Only one thread can use it at a time. Example Lock, file handle, database connection.
If resources were shareable, deadlock wouldn't occur.
2. Hold and Wait : A thread holds at least one resource AND is waiting to acquire additional
resources. Example -> Thread T1 holds Lock A and waiting for Lock B.
3. No Preemption : Resources cannot be forcibly taken away. A lock cannot be stolen, Thread must
release it voluntarily. Ex -> If the system could force-release locks -> no deadlock.
4. Circular wait : There exists a circular chain of threads.
ex -> T1 waits for resource held by T2. T2 waits for resource held by T2 and T3 waits for resource
held by T1.

So in the above code we have:
Mutual exclusion -> Locks are exclusive
Hold and wait -> each thread holds one lock
No preemption -> locks can't be taken away
Circular wait -> T1 waits for T2, T2 waits for T1

Prevent Deadlock :
1. Break Circular Wait : Use lock ordering. Always acquire locks in the same order.
2. Avoid Hold and wait : Acquire all required locks at once
3. Use timeout : With tryLock()
4. Use Fair Locks : reduce starvation, not deadlock directly.
 */