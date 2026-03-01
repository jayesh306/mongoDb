package com.example.mongodbDemo;

import com.example.mongodbDemo.entity.Address;
import com.example.mongodbDemo.entity.Order;
import com.example.mongodbDemo.entity.Product;
import com.example.mongodbDemo.repository.OrderRepo;
import com.example.mongodbDemo.repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class SimpleMongodbTests {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepo productRepo;

    @Test
    public void testCreateOrder() {
        for (int i = 0; i < 10; i++) {
            Order order = Order.builder().status("Ready").quantity(2 * i).totalPrice(100.0 * i).build();

            order = orderRepo.insert(order);
            System.out.println(order);
        }
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
    @Test
    public void getQueryTest(){
        List<Order> orderList = orderRepo.findOrderByStatusAboveAndPrice("Ready",200);
        orderList.forEach(System.out::println);
    }
    @Test
    public void testPageable(){
        Pageable pageRequest = PageRequest.of(0,5);
        List<Order> orderList = orderRepo.findAll(pageRequest).toList();
        orderList.forEach(System.out::println);
    }
    @Test
    public void testPageableBySorting(){
        Pageable pageRequest = PageRequest.of(1,5).withSort(Sort.Direction.DESC,"totalPrice");
        List<Order> orderList = orderRepo.findAll(pageRequest).toList();
        orderList.forEach(System.out::println);
    }
    @Test
    public void testRelationship(){
        //Embedding
        Order order = Order.builder().status("Ready").quantity(2).totalPrice(100.0)
                .address(Address.builder().line1("Line 1 Address").city("Delhi").state("Delhi").build()).build();
        order = orderRepo.insert(order);
    }
    @Test
    public void testReferencing(){
        //Referencing
        List<Order> orderList = orderRepo.findByAddress_City("Delhi");
        orderList.forEach(System.out::println);
    }
    @Test
    public void testOrderAndProductCreation(){
        Product laptop = Product.builder().name("Gaming Laptop").category("Electronics").price(1299.99).build();
        laptop = productRepo.insert(laptop);
        Product phone = Product.builder().name("Iphone 22").category("Electronics").price(1699.99).build();
        phone = productRepo.save(phone);
        Order order = Order.builder().status("Ready").quantity(2).totalPrice(100.0).products(List.of(laptop,phone))
                .address(Address.builder().line1("Line 1 Address").city("Delhi").state("Delhi").build()).build();
        order = orderRepo.save(order);
        System.out.println(order);
    }
    @Test
    public void testProjection(){
        var orders = orderRepo.findByCity("Delhi");
        orders.forEach((System.out::println));
    }
}
