package com.example.Java8Lab.functional;

@FunctionalInterface
public interface PriceCalculator{
    double calculate(double basePrice);
}
