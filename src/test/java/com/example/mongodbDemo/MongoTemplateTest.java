package com.example.mongodbDemo;


import com.example.mongodbDemo.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootTest
public class MongoTemplateTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void mongoTemplateTest(){
        List<Order> orderList = mongoTemplate.findAll(Order.class);
        orderList.forEach(System.out::println);
    }
    @Test
    public void mongoTemplateFindByQuery(){
        Query query = new Query(Criteria.where("status").in("pending").and("totalPrice").gt(120));
        List<Order> orderList = mongoTemplate.find(query,Order.class);
        orderList.forEach(System.out::println);
    }
    @Test
    public void testingOrOperator(){
        Query query = new Query(new Criteria().orOperator(Criteria.where("totalPrice").lte(120),
                Criteria.where("status").is("pending")));
        //we can also add projection in these mongoTemplate queries for more complex operations
        query.fields().include("status","id");
        query.limit(2);
        List<Order> orderList = mongoTemplate.find(query,Order.class);
        orderList.forEach(System.out::println);
    }
}
