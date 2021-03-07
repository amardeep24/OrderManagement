package com.amardeep.ordermanagement.controller;


import com.amardeep.ordermanagement.dto.OrderDTO;
import com.amardeep.ordermanagement.entity.Customer;
import com.amardeep.ordermanagement.entity.Inventory;
import com.amardeep.ordermanagement.entity.Orders;
import com.amardeep.ordermanagement.entity.Product;
import com.amardeep.ordermanagement.service.CustomerService;
import com.amardeep.ordermanagement.service.InventoryService;
import com.amardeep.ordermanagement.service.OrderService;
import com.amardeep.ordermanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class OrdersController {

    @Autowired
    OrderService ordersService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    InventoryService inventoryService;

    @GetMapping(value="orders/{customerId}",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Orders>> getAllOrdersByCustomer(@PathVariable("customerId") String customerId){
        List<Orders> orderList=ordersService.getAllOrdersByCustomerId(customerId).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping(value="order/{orderId}",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> getOrderById(@PathVariable("orderId") Long orderId){
        Orders order = ordersService.getOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping(value="order",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> createOrder(@RequestBody OrderDTO orderDTO, UriComponentsBuilder builder){
        Product product = productService.getProductById(orderDTO.getProductId());
        Customer customer = customerService.getCustomerById(orderDTO.getCustomerId());
        Inventory inventory = inventoryService.getInventoryByProduct(product);
        Orders order = new Orders();
        order.setQuantity(orderDTO.getQuantity());
        order.setCustomer(customer);
        order.setProduct(product);
        order.setOrderTotal(inventory.getPrice() * orderDTO.getQuantity());
        order.setOrderDate(new Date());
        Orders newOrder = ordersService.createOrder(order);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/order/{orderId}").buildAndExpand(newOrder.getId()).toUri());
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }
}
