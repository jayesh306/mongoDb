package com.example.mongodbDemo.repository;

import com.example.mongodbDemo.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product,String> {
}
