package com.pavlov.controllers;

import com.pavlov.model.Order;
import com.pavlov.model.OrderDetails;
import com.pavlov.repository.OrderDetailRepository;
import com.pavlov.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @GetMapping("/api/get-time")
    public ResponseEntity getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> result = new HashMap();
        result.put("currentDate", sdf.format(new Date(System.currentTimeMillis())));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/orders")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("/api/order")
    public ResponseEntity getOrderById(@RequestParam Long orderid) {
        return ResponseEntity.ok(orderRepository.findById(orderid));
    }

    @PutMapping("/api/order")
    public ResponseEntity addOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @Transactional
    @PostMapping("/api/order")
    public ResponseEntity updateOrder(@RequestBody Order order) {
        Optional<Order> orderFromDb = orderRepository.findById(order.getId());
        if (!orderFromDb.isPresent()) return ResponseEntity.badRequest().body("Illegal order");
        Order updateOrder = orderFromDb.get();
        updateOrder.setCustomername(order.getCustomername());
        updateOrder.setCustomeraddr(order.getCustomeraddr());
        updateOrder.setCreatedate(order.getCreatedate());
        updateOrder.setOrdersum(order.getOrdersum());
        return ResponseEntity.ok(orderRepository.save(updateOrder));
    }

    @Transactional
    @DeleteMapping("/api/order")
    public ResponseEntity deleteOrder(@RequestParam Long id) {
        detailRepository.deleteAll(detailRepository.findAllByOrderid(id));
        orderRepository.deleteById(id);
        return ResponseEntity.ok("Order deleted");
    }

    @GetMapping("/api/all-details")
    public ResponseEntity getAllOrderDetails() {
        return ResponseEntity.ok(detailRepository.findAll());
    }

    @GetMapping("/api/details")
    public ResponseEntity getOrderDetailsByOrderId(@RequestParam Long orderid) {
        return ResponseEntity.ok(detailRepository.findAllByOrderid(orderid));
    }

    @GetMapping("/api/detail")
    public ResponseEntity getOrderDetailById(@RequestParam Long detailid) {
        return ResponseEntity.ok(detailRepository.findById(detailid));
    }

    @PutMapping("/api/detail")
    public ResponseEntity addOrderDetails(@RequestBody OrderDetails details) {
        return ResponseEntity.ok(detailRepository.save(details));
    }

    @Transactional
    @PostMapping("/api/detail")
    public ResponseEntity updateOrderDetails(@RequestBody OrderDetails details) {
        Optional<OrderDetails> detailFromDb = detailRepository.findById(details.getDetailid());
        if (!detailFromDb.isPresent()) return ResponseEntity.badRequest().body("Illegal detail");
        OrderDetails updateDetail = detailFromDb.get();
        updateDetail.setOrderid(details.getOrderid());
        updateDetail.setProductserialnum(details.getProductserialnum());
        updateDetail.setProductname(details.getProductname());
        updateDetail.setCount(details.getCount());
        return ResponseEntity.ok(detailRepository.save(updateDetail));
    }

    @DeleteMapping("/api/detail")
    public ResponseEntity deleteOrderDetails(@RequestParam Long detailId) {
        detailRepository.deleteById(detailId);
        return ResponseEntity.ok(String.format("Detail with id = %d deleted successful", detailId));
    }
}