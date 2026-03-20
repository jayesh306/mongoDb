package com.example.Java8Lab.interfaces.defaultmethoddemo;

public interface TaxCalculator {
    static double calculateGST(double amount){
        return amount*1.18;
    }
}
