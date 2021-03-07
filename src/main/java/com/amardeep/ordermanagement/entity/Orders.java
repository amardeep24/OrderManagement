package com.amardeep.ordermanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="ORDERS")
public class Orders {
    @Id
    @Column(name="ORDER_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="ORDER_DATE")
    private Date orderDate;

    @JoinColumn(name="CUSTOMER_ID",nullable=false)
    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name="PRODUCT_ID",nullable=false)
    private Product product;

    @Column(name="ORDER_TOTAL")
    private Double orderTotal;
    @Column(name="QUANTITY")
    private Integer quantity;

    public Orders() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id) && Objects.equals(orderDate, orders.orderDate) && Objects.equals(customer, orders.customer) && Objects.equals(product, orders.product) && Objects.equals(orderTotal, orders.orderTotal) && Objects.equals(quantity, orders.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, customer, product, orderTotal, quantity);
    }
}
