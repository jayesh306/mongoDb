package com.example.mongodbDemo.repository;

import com.example.mongodbDemo.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends MongoRepository<Order,String> {

    List<Order> findByStatusAndQuantityGreaterThan(String status, Integer quantity);
    List<Order> findByTotalPriceGreaterThan(double price);
    List<Order> findByStatusAndQuantityLessThan(String status,Integer quanity);

    List<Order> findByStatusAndQuantityGreaterThanOrderByCreatedAtDesc(String status,Integer quantity);

    @Query("{'status': ?0, 'totalPrice': { $gte: ?1 } }")
    List<Order> findOrderByStatusAboveAndPrice(String status, double minPrice);

    List<Order> findByAddress_City(String city);

    //Projection
    @Query(value = "{ 'address.city' : ?0}",fields = "{'_id' :  1, 'quantity': 1, 'city': 1}")
    List<Order> findByCity(String city);

}
