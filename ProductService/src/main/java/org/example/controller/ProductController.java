package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CreateProductRequestDto;
import org.example.dto.CreateProductResponseDto;
import org.example.dto.GetProductDto;
import org.example.exception.ProductNotFoundException;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/user/{userId}")
    public List<GetProductDto> getProductsByUserId(@PathVariable("userId") Long id) throws ProductNotFoundException {
        return productService.getProductsByUserId(id);
    }

    @GetMapping("/{productId}")
    public GetProductDto getProductById(@PathVariable("productId") Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @PostMapping("/")
    public CreateProductResponseDto saveProduct(@RequestBody CreateProductRequestDto product) {
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