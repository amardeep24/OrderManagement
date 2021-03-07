package com.amardeep.ordermanagement.service;

import com.amardeep.ordermanagement.entity.Customer;
import com.amardeep.ordermanagement.entity.Orders;
import com.amardeep.ordermanagement.entity.Product;
import com.amardeep.ordermanagement.repository.OrdersRepository;
import com.amardeep.ordermanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrdersRepository ordersRepository;

    public List<Orders> getAllOrdersByCustomerId(String customerId) {
        return ordersRepository.findOrdersByCustomer_Id(customerId);
    }

    public Orders createOrder(Orders order) {
        return ordersRepository.save(order);
    }

    public Orders getOrder(Long id){
        return ordersRepository.findById(id).get();
    }
}
