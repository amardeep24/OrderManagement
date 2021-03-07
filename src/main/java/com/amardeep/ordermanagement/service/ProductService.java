package com.amardeep.ordermanagement.service;

import com.amardeep.ordermanagement.entity.Product;
import com.amardeep.ordermanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> productList=productRepository.findAll();
        return productList;
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).get();
    }
}
