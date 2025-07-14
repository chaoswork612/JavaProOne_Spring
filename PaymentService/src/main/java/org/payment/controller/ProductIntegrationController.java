package org.payment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.payment.dto.GetProductIntegrationResponseDto;
import org.payment.exception.ProductNotFoundException;
import org.payment.service.ProductIntegrationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/payments/products/")
@RequiredArgsConstructor
public class ProductIntegrationController {
    private final ProductIntegrationService productIntegrationService;
    @GetMapping("/{productId}")
    public GetProductIntegrationResponseDto getProductById(@PathVariable("productId") Long id) throws ProductNotFoundException {
        return productIntegrationService.getProductById(id);
    }

    @GetMapping("/user/{userId}")
    public List<GetProductIntegrationResponseDto> getProductsByUserId(@PathVariable("userId") Long id) throws ProductNotFoundException {
        return productIntegrationService.getProductsByUserId(id);
    }

}
