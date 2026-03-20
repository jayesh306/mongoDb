package com.example.Java8Lab.streams;

import com.example.Java8Lab.BaseRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
@Order(9)
public class ParallelStreamRunner extends BaseRunner {
    @Override
    protected void execute() {
        System.out.println("=== 1. Sequential Vs Parallel Performance ===");
        performanceComparison();

        System.out.println("\n=== 2. forEach vs forEachOrdered ===");
        forEachComparison();

        System.out.println("\n=== 3. Thread Safety Problem Demo");
        threadSafetyIssue();
    }

    private void performanceComparison(){
        long start1 = System.nanoTime();
        long sum1 = IntStream.rangeClosed(1,5_000_000).sum();
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        long sum2 = IntStream.rangeClosed(1,5_000_000).parallel().sum();
        long end2 = System.nanoTime();

        System.out.println("Sequential time : "+(end1-start1));
        System.out.println(("Parallel time : "+(end2-start2)));
        System.out.println("Results equal? "+(sum1==sum2));
    }

    private void forEachComparison(){
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8);
        System.out.println("forEach (parallel):");
        numbers.parallelStream().forEach(System.out::print);

        System.out.println("\nforEachOrdered (parallel): ");
        numbers.parallelStream().forEachOrdered(System.out::print);
        System.out.println();
    }

    private void threadSafetyIssue(){
        List<Integer> numbers = List.of(1,2,3,4,5);
        StringBuilder builder = new StringBuilder();
        numbers.parallelStream().forEach(builder::append);  //Not thread-safe
        System.out.println("Unsafe result : "+builder);
    }
}
/* When we use .parallelStream(), internally it uses ForkJoinPool.commonPool(). So it splits data
into chunks and processes them in parallel threads. So parallel streams use the ForkJoinPool
common pool, and that pool uses the Work-Stealing Algorithm internally.
In ForkJoinPool, each worker thread has its own deque, it pushes and pops its own tasks. If a
thread finishes its work, it "steals" tasks from another busy thread.
We can use Parallel Streams for CPU-intensive tasks, large data sets, stateless operations
and associative reductions.
We shouldn't use Parallel Streams when dealing with small data sets, I/O operations(DB/HTTP calls),
shared mutable state and non-associative reduction.
Parallel is not always faster. Overhead can include Thread scheduling, Context switching, Splitting and Merging.

 */