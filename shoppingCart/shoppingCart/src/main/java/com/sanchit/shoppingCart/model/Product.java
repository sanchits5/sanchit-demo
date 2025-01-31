package com.sanchit.shoppingCart.model;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long productId;
    private String productName;
    private Long productQuantity;
    private Long productPrice;


}
