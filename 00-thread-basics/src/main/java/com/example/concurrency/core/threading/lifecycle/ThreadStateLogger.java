package com.example.concurrency.core.threading.lifecycle;

public class ThreadStateLogger {
    public static void log(String action, Thread thread){
        System.out.printf("[%s] | Thread = %s | State = %s%n",
                action,
                thread.getName(),
                thread.getState());
    }
}
