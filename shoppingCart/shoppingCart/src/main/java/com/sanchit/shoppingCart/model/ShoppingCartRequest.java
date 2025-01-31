package com.sanchit.shoppingCart.model;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppingCartRequest {
    private Long productId;
    private Long quantity;


}
