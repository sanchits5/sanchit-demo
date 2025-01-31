package com.sanchit.shoppingCart.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanchit.shoppingCart.entity.Cart;
import com.sanchit.shoppingCart.model.Product;
import com.sanchit.shoppingCart.model.ShoppingCartRequest;
import com.sanchit.shoppingCart.model.ShoppingCartResponse;
import com.sanchit.shoppingCart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {
    @Autowired
    @Qualifier("webClient")
    private WebClient.Builder webBuilder;

    @Autowired
    private CartRepository cartRepository;

    public ShoppingCartResponse processAddRequest(Long userId, List<ShoppingCartRequest> shoppingCartRequestList)  {
        ObjectMapper mapper = new ObjectMapper();

        String productUrl = "http://product-service/product/getProduct/" + shoppingCartRequestList.stream().map(e -> String.valueOf(e.getProductId())).collect(Collectors.joining(","));
        List<Product> processProductsList = webBuilder.build()
                .get()
                .uri(productUrl)
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList()
                .block();

        System.out.println("Product List: "+processProductsList);

        List<String> result1 = processProductsList.stream()
                .map(Product::getProductName)
                .collect(Collectors.toList());
        List<Long> result2=processProductsList.stream().map(Product::getProductPrice).collect(Collectors.toList());

        List<String> finalString = new ArrayList<>();
        for (int i = 0; i < Math.min(result1.size(), result2.size()); i++) {
            finalString.add(result1.get(i) +": "+ result2.get(i));
        }

        System.out.println("final result: "+finalString);

        final Double[] totalCost = {0.0};
        processProductsList.forEach(psl ->
        {
            shoppingCartRequestList.forEach(scr ->
            {
                if (psl.getProductId() == scr.getProductId()) {
                    psl.setProductQuantity(scr.getQuantity());
                    totalCost[0] = totalCost[0] + psl.getProductPrice() * scr.getQuantity();
                }
            });
        });
        Cart cartEntity = null;
        try {
            cartEntity = Cart.builder()
                    .userId(userId)
                    .cartId((long) (Math.random() * Math.pow(10, 10)))
                    .totalItems(processProductsList.size())
                    .totalCost(totalCost[0])
                    .products(finalString.toString())
                    .build();
            System.out.println("Value: "+Cart.builder().products(mapper.writeValueAsString(processProductsList)));
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
        // save CartEntity
        cartEntity = cartRepository.save(cartEntity);

        // create api response

        ShoppingCartResponse response=ShoppingCartResponse.builder()
                .userId(cartEntity.getUserId())
                .cartId(cartEntity.getCartId())
                .totalItems(cartEntity.getTotalItems())
                .totalCost(cartEntity.getTotalCost())
                .products(processProductsList)
                .build();
        return response;
    }

    public List<ShoppingCartResponse> getCartDetails(Long userId) {
        ObjectMapper m=new ObjectMapper();
        List<Cart> cartResponse=cartRepository.findByUserId(userId);
        List<ShoppingCartResponse> shoppingCartResponse=cartResponse.stream()
                .map(ce-> {
                    try {
                        return ShoppingCartResponse.builder()
                                .userId(ce.getUserId())
                                .cartId(ce.getCartId())
                                .totalItems(ce.getTotalItems())
                                .totalCost(ce.getTotalCost())
                                .products(m.readValue(ce.getProducts(),List.class))
                                .build();
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
        return shoppingCartResponse;
    }
}