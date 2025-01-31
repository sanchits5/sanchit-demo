package com.shopCart.product_service.service;

import com.shopCart.product_service.model.Product;
import com.shopCart.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository=productRepository;
    }


    public Product addProduct(Product product) {
        Product productAdd=productRepository.save(product);
        return product;
    }

    public List<Product> getAllProducts() {
        List<Product> getAllProducts=productRepository.findAll();
        return getAllProducts;
    }

    public List<Product> getProductByProductId(List<Long> productId) {
        List<Product> getProductById=productRepository.findAllById(productId);
        return getProductById;
    }
}
