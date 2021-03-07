package com.amardeep.ordermanagement.service;

import com.amardeep.ordermanagement.entity.Customer;
import com.amardeep.ordermanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomerById(String customerId){
        return customerRepository.findById(customerId).orElseGet(() -> null);
    }
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
