package com.amardeep.ordermanagement.repository;

import com.amardeep.ordermanagement.entity.Customer;
import com.amardeep.ordermanagement.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders>  findOrdersByCustomer_Id(String customerId);
}
