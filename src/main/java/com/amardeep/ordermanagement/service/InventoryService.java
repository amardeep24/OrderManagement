package com.amardeep.ordermanagement.service;

import com.amardeep.ordermanagement.entity.Inventory;
import com.amardeep.ordermanagement.entity.Product;
import com.amardeep.ordermanagement.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public Inventory getInventoryByProduct(Product product){
        return inventoryRepository.findInventoryByProduct(product);
    }
}
