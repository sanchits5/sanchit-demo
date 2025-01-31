package com.sanchit.shoppingCart.model;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShoppingCartResponse {
    private Long userId;
    private Long cartId;
    private Integer totalItems;
    private Double totalCost;
    private List<Product> products;


}
