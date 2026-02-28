package com.example.mongodbDemo.controller;

import com.example.mongodbDemo.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/test")
    public String testConnection(){
        long count = orderRepo.count();
        return "MongoDb connected! Order count = "+count;
    }
}
