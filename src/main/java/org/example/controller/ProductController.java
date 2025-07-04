package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CreateProductRequestDto;
import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/user/{userId}")
    public List<Product> getProductsByUserId(@PathVariable("userId") Long id) {
        return productService.getProductsByUserId(id);
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable("productId") Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/")
    public Product saveProduct(@RequestBody CreateProductRequestDto product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable("productId") Long id) {
        productService.deleteProduct(id);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteProductsByUserId(@PathVariable("userId") Long id) {
        productService.deleteProductsByUserId(id);
    }
}