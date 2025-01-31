package com.sanchit.shoppingCart.repository;

import com.sanchit.shoppingCart.entity.Cart;
import com.sanchit.shoppingCart.model.ShoppingCartResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findByUserId(Long userId);
}
