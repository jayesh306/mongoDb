package com.example.concurrency.fork.join.basics;

import java.util.concurrent.RecursiveTask;
/* This class calculates sum of array in parallel.
fork() -> submit task asynchronously.
join() -> wait for result
*/
public class ParallelSumTask extends RecursiveTask<Long>
{
    private final int[] array;
    private final int start;
    private final int end;

    private static final int THRESHOLD = 500;

    public ParallelSumTask(int[] array, int start, int end){
        this.array = array;
        this.start = start;
        this.end=end;
    }

    @Override
    protected Long compute() {
        if(end-start <= THRESHOLD){
            long sum=0;
            for(int i=start; i<end;i++){
                sum+=array[i];
            }
            return sum;
        }
        int mid = (start+end)/2;
        ParallelSumTask leftTask = new ParallelSumTask(array,start,mid);
        ParallelSumTask rightTask = new ParallelSumTask(array,mid,end);
        leftTask.fork();
        long rightResult = rightTask.compute();
        long leftResult = leftTask.join();
        return leftResult + rightResult;
    }
}
