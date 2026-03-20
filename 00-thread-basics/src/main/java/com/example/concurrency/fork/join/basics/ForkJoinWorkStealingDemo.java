package com.example.concurrency.fork.join.basics;

import java.util.concurrent.RecursiveAction;

public class ForkJoinWorkStealingDemo extends RecursiveAction {
    private final int workload;
    public ForkJoinWorkStealingDemo(int workload){
        this.workload=workload;
    }
    @Override
    protected void compute() {
        if(workload>16){
            System.out.println(Thread.currentThread().getName()+" splitting workload : "+workload);
            int half = workload/2;
            ForkJoinWorkStealingDemo task1 = new ForkJoinWorkStealingDemo(half);
            ForkJoinWorkStealingDemo task2 = new ForkJoinWorkStealingDemo(half);
            invokeAll(task1,task2);
        }else{
            System.out.println(Thread.currentThread().getName()+" processing workload : "+workload);
        }
    }
}
