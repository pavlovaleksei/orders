package com.pavlov.repository;

import com.pavlov.model.OrderDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetails, Long> {

    List<OrderDetails> findOrderDetailsByOrderid(Long orderid);

}
