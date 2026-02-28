package com.example.mongodbDemo.repository;

import com.example.mongodbDemo.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends MongoRepository<Order,String> {

    List<Order> findByStatusAndQuantityGreaterThan(String status, Integer quantity);
    List<Order> findByTotalPriceGreaterThan(double price);
    List<Order> findByStatusAndQuantityLessThan(String status,Integer quanity);

    List<Order> findByStatusAndQuantityGreaterThanOrderByCreatedAtDesc(String status,Integer quantity);
}
