package com.pavlov.controllers;

import com.pavlov.model.Order;
import com.pavlov.model.OrderDetails;
import com.pavlov.repository.OrderDetailRepository;
import com.pavlov.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Pavlov Aleksei
 * @Date 28-08-2020
 * HTTP api controllers
 */
@RestController
@EnableJpaRepositories("com.pavlov.repository")
public class HttpController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository detailRepository;


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/get-time")
    public ResponseEntity getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> result = new HashMap();
        result.put("currentDate", sdf.format(new Date(System.currentTimeMillis())));
        return ResponseEntity.ok(result);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/orders")
    public ResponseEntity getAllOrders() {
        System.out.println("getAllOrders");
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/order")
    public ResponseEntity getOrderById(@RequestParam Long orderid) {
        System.out.println("getOrderById");
        return ResponseEntity.ok(orderRepository.findById(orderid));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/api/order")
    public ResponseEntity addOrder(@RequestBody Order order) {
        System.out.println("Order: " + order.toString());
        System.out.println("addOrder");
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/api/order")
    public ResponseEntity updateOrder(@RequestBody Order order) {
        System.out.println("updateOrder");
        orderRepository.delete(order);
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/api/order")
    public ResponseEntity deleteOrder(@RequestParam Long id) {
        System.out.println("deleteOrder");
        orderRepository.deleteById(id);
        return ResponseEntity.ok("Order deleted");
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/all-details")
    public ResponseEntity getAllOrderDetails() {
        System.out.println("getAllOrderDetails");
        return ResponseEntity.ok(detailRepository.findAll());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/details")
    public ResponseEntity getOrderDetailsByOrderId(@RequestParam Long orderid) {
        System.out.println("getOrderDetailsByOrderId");
        return ResponseEntity.ok(detailRepository.findAllByOrderid(orderid));
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/detail")
    public ResponseEntity getOrderDetailById(@RequestParam Long detailid) {
        System.out.println("getOrderDetailById: " + detailid);
        return ResponseEntity.ok(detailRepository.findById(detailid));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/api/detail")
    public ResponseEntity addOrderDetails(@RequestBody OrderDetails details) {
        System.out.println("addOrderDetails");
        return ResponseEntity.ok(detailRepository.save(details));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/api/detail")
    public ResponseEntity updateOrderDetails(@RequestBody OrderDetails details) {
        System.out.println("updateOrderDetails");
        detailRepository.delete(details);
        return ResponseEntity.ok(detailRepository.save(details));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/api/detail")
    public ResponseEntity deleteOrderDetails(@RequestParam Long detailId) {
        System.out.println("deleteOrderDetails");
        detailRepository.deleteById(detailId);
        return ResponseEntity.ok(String.format("Detail with id = %d deleted successful", detailId));
    }
}