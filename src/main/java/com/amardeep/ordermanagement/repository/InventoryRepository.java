package com.amardeep.ordermanagement.repository;

import com.amardeep.ordermanagement.entity.Inventory;
import com.amardeep.ordermanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    public Inventory findInventoryByProduct(Product product);
}
