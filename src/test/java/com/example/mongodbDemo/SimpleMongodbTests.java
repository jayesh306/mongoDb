package com.example.mongodbDemo;

import com.example.mongodbDemo.entity.Order;
import com.example.mongodbDemo.repository.OrderRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SimpleMongodbTests {

    @Autowired
    private OrderRepo orderRepo;

    @Test
    public void testCreateOrder(){
        Order order = Order.builder().status("Ready").quantity(10).totalPrice(100.0).build();

        order = orderRepo.insert(order);
        System.out.println(order);
    }
    @Test
    public void testGetOrder(){
        List<Order> orderList = orderRepo.findByStatusAndQuantityGreaterThan("Ready",2);
        System.out.println(orderList);
    }
    @Test
    public void testGetPrice(){
        List<Order> orderListForPriceGreaterThan = orderRepo.findByTotalPriceGreaterThan(200);
        orderListForPriceGreaterThan.forEach(System.out::println);
    }
    @Test
    public void testByQuantityAndStatus(){
        List<Order> orderListByQuantityAndStatus = orderRepo.findByStatusAndQuantityLessThan("Ready",20);
        orderListByQuantityAndStatus.forEach(System.out::println);
    }
    @Test
    public void testGetOrderByDesc(){
        List<Order> orderListByDesc = orderRepo.findByStatusAndQuantityGreaterThanOrderByCreatedAtDesc("Ready",2);
        orderListByDesc.forEach(System.out::println);
    }
}
