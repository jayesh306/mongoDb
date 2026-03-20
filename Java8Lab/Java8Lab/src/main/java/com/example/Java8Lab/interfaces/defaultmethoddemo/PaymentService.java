package com.example.Java8Lab.interfaces.defaultmethoddemo;

public interface PaymentService {

    void processPayment(double amount);
    default void printReceipt(double amount){
        System.out.println("Recipt generated for amount: "+amount);
    }
}
