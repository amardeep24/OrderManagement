package com.amardeep.ordermanagement.repository;

import com.amardeep.ordermanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
