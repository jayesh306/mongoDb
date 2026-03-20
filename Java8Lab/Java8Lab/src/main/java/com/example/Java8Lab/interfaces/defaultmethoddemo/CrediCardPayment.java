package com.example.Java8Lab.interfaces.defaultmethoddemo;

public class CrediCardPayment implements PaymentService{
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment: "+amount);
    }
}
