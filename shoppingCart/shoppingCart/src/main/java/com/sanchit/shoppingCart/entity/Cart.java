package com.sanchit.shoppingCart.entity;

import com.sanchit.shoppingCart.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

import java.util.List;


@Entity
@Builder
@Table(name = "SHOPPING_CART")
public class Cart {

    @Id
    private Long userId;
    private Long cartId;
    private Integer totalItems;
    private Double totalCost;
    private String products;

    public Cart(Long userId, Long cartId, Integer totalItems, Double totalCost, String products) {
        this.userId = userId;
        this.cartId = cartId;
        this.totalItems = totalItems;
        this.totalCost = totalCost;
        this.products = products;
    }
    public Cart()
    {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }



    @Override
    public String toString() {
        return "Cart{" +
                "userId=" + userId +
                ", cartId=" + cartId +
                ", totalItems=" + totalItems +
                ", totalCost=" + totalCost +
                ", products='" + products + '\'' +
                '}';
    }
}
