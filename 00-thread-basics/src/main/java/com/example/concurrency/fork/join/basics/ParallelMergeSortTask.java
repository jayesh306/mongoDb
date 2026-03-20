package com.example.concurrency.fork.join.basics;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
/* RecursiveTask -> returns value.
RecursiveAction -> no return value
 */
public class ParallelMergeSortTask extends RecursiveAction {
    private final int[] array;
    private final int start;
    private final int end;

    private static final int THRESHOLD = 1000;

    public ParallelMergeSortTask(int[] array, int start, int end){
        this.array=array;
        this.start=start;
        this.end=end;
    }
    @Override
    protected void compute() {
        if(end-start<=THRESHOLD){
            Arrays.sort(array,start,end);
            return;
        }
        int mid = (start+end)/2;

        ParallelMergeSortTask left = new ParallelMergeSortTask(array,start,mid);
        ParallelMergeSortTask right = new ParallelMergeSortTask(array,mid,end);
        invokeAll(left,right);
    }
}
