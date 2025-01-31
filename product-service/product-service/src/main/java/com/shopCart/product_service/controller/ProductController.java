package com.shopCart.product_service.controller;

import com.shopCart.product_service.model.Product;
import com.shopCart.product_service.service.ProductService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private  ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService=productService;
    }

    @PostMapping("/seller/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product)
    {
        Product p=productService.addProduct(product);
        return new ResponseEntity<Product>(p,HttpStatus.CREATED);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts()
    {
        List<Product> productsList=productService.getAllProducts();
        if(productsList!=null) {
            return new ResponseEntity<>(productsList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/getProduct/{productId}")
    public ResponseEntity<List<Product>> getProductByProductId(@PathVariable List<Long> productId)
    {
        List<Product> productDetail=productService.getProductByProductId(productId);
        if(productDetail!=null) {
            return new ResponseEntity<>(productDetail, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
