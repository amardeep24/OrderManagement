package com.amardeep.ordermanagement.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="INVENTORY")
public class Inventory {
    @Id
    @Column(name="INVENTORY_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="PRODUCT_ID",nullable=false)
    private Product product;
    @Column(name="QUANTITY")
    private Integer quantity;
    @Column(name="PRICE")
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(id, inventory.id) && Objects.equals(product, inventory.product) && Objects.equals(quantity, inventory.quantity) && Objects.equals(price, inventory.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, quantity, price);
    }
}
