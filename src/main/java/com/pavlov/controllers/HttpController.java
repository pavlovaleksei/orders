package com.pavlov.controllers;

import com.pavlov.model.OrderDetails;
import com.pavlov.model.Result;
import com.pavlov.repository.OrderDetailRepository;
import com.pavlov.repository.OrderRepository;
import org.hibernate.Hibernate;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableJpaRepositories("com.pavlov.repository")
public class HttpController {

    @Autowired
    DataSource dataSource;


    @Autowired
    OrderRepository systemRepository;

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
    @GetMapping("/api/ordersh")
    public ResponseEntity getAllOrdersH() {


//        List<Result> lists = new Configuration().buildSessionFactory().openSession().createQuery("select new com.pavlov.model.Result(orders.customername, orders.customeraddr) from Order orders").list();

//        var orders = (List<OrderH>) orderService.findAll();
        System.out.println("getAllOrdersh");

        Iterable<OrderDetails> detailsList = detailRepository.findAll();
        detailsList.forEach(v -> System.out.println(v.toString()));

//        Session session = new Configuration().buildSessionFactory().openSession();
//        List<Order> list = session.createQuery("select new com.pavlov.model.Order(orders.id, orders.customername, orders.customeraddr, orders.ordersum, orders.createdate) from Order orders").list();




        return ResponseEntity.ok(systemRepository.findAll());
//        return ResponseEntity.ok(lists);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/orders")
    public ResponseEntity getAllOrders() {
        System.out.println("getAllOrders");
        return ResponseEntity.ok(systemRepository.findAll());
    }

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @GetMapping("/api/orders/{id}")
//    public ResponseEntity getOrderById(@PathVariable("id") int id) {
//        System.out.println("getOrderById");
//        return ResponseEntity.ok(OrderRepository.orders.get(id));
//    }
//
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @PostMapping("/api/orders")
//    public void updateOrder(@RequestParam Order order) {
//        OrderRepository.orders.set(order.getId(), order);
//    }
//
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @PutMapping("/api/orders")
//    public void addOrder(@RequestParam Order order) {
//        OrderRepository.orders.add(order);
//    }
//
//
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @DeleteMapping("/api/orders")
//    public void deleteOrderById(@RequestParam int id) {
//        OrderRepository.orders.remove(id);
//    }

}
