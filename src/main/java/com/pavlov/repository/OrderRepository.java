package com.pavlov.repository;

import com.pavlov.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Pavlov Aleksei
 * @Date 28-08-2020
 * Repository for orders
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
