package com.sanchit.shoppingCart.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sanchit.shoppingCart.exportPDF.PdfService;
import com.sanchit.shoppingCart.model.ShoppingCartRequest;
import com.sanchit.shoppingCart.model.ShoppingCartResponse;
import com.sanchit.shoppingCart.service.ShoppingCartService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private  PdfService pdfService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/{userId}/products")
    public ResponseEntity<ShoppingCartResponse> addProductsToCart(
            @PathVariable Long userId,
            @RequestBody List<ShoppingCartRequest> shoppingCartRequestList
            ) {
        ShoppingCartResponse response=shoppingCartService.processAddRequest(
                userId,shoppingCartRequestList);
        return new ResponseEntity<ShoppingCartResponse>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getCartDetails/{userId}")
    public ResponseEntity<List<ShoppingCartResponse>> getCartDetails(@PathVariable Long userId)
    {
        List<ShoppingCartResponse> response=shoppingCartService.getCartDetails(userId);
       return ResponseEntity.ok(response);
    }

    @GetMapping("/getCartDetails/{userId}/download")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long userId) {
        byte[] pdfContents = pdfService.generatePdf(userId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sample.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfContents);
    }
}