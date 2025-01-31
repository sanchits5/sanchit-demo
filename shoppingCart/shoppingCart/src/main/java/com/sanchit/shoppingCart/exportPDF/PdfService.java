package com.sanchit.shoppingCart.exportPDF;

import com.itextpdf.kernel.pdf.*;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;
import com.sanchit.shoppingCart.entity.Cart;
import com.sanchit.shoppingCart.model.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.sanchit.shoppingCart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class PdfService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    @Qualifier("webClient")
    private WebClient.Builder webBuilder;



        public byte[] generatePdf(Long userId) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            List<Cart> cartResponse=cartRepository.findByUserId(userId);



            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Add title
            document.add(new Paragraph("Cart Details").setFontSize(14));

            // Create table with 5 columns
            Table table = new Table(UnitValue.createPercentArray(new float[]{3, 3, 3, 3, 3}))
                    .useAllAvailableWidth();

            // Add table headers
            table.addHeaderCell(new Cell().add(new Paragraph("User_Id")));
            table.addHeaderCell(new Cell().add(new Paragraph("Cart_Id")));
            table.addHeaderCell(new Cell().add(new Paragraph("Total_Cost")));
            table.addHeaderCell(new Cell().add(new Paragraph("Total_Items")));
            table.addHeaderCell(new Cell().add(new Paragraph("Products")));


            // Sample data rows
           for(Cart cart:cartResponse) {
               table.addCell(String.valueOf(cart.getUserId()));
               table.addCell(String.valueOf(cart.getCartId()));
               table.addCell(String.valueOf(cart.getTotalCost()));
               table.addCell(String.valueOf(cart.getTotalItems()));

               table.addCell(String.valueOf(cart.getProducts()));
           }
            // Add table to document
            document.add(table);

            document.close();

            return outputStream.toByteArray();
        }
}
