package com.example.Java8Lab.interfaces.diamondproblem;

public class C implements A,B{
    @Override
    public void display(){
        A.super.display(); //Explicit resolution
    }
}

//you must override, otherwise -> compilation error