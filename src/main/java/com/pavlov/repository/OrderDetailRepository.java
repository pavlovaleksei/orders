package com.pavlov.repository;

import com.pavlov.model.OrderDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Pavlov Aleksei
 * @Date 28-08-2020
 * Repository for order details
 */
@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetails, Long> {

    /**
     * Method for get all order details by orderId
     * Interpretation DML query: SELECT * FROM details WHERE orderid = orderid;
     * @param orderId ID order
     * @return List result from query
     */
    List<OrderDetails> findAllByOrderid(Long orderId);
}
