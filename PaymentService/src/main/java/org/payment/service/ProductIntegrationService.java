package org.payment.service;

import lombok.RequiredArgsConstructor;
import org.payment.dto.GetProductIntegrationResponseDto;
import org.payment.dto.GetProductResponseDto;
import org.payment.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductIntegrationService {
    private final RestTemplate productsClient;

    public GetProductIntegrationResponseDto getProductById(Long productId) throws ProductNotFoundException {
        try {
            GetProductResponseDto responseDto = productsClient.getForObject(
                    "/api/products/{productId}",
                    GetProductResponseDto.class,
                    productId
            );
            return new GetProductIntegrationResponseDto(
                    Objects.requireNonNull(responseDto).id(),
                    responseDto.productType(),
                    responseDto.balance()
            );
        }
        catch (HttpClientErrorException ex) {
            throw new ProductNotFoundException("Product was not found");
        }
    }

    public List<GetProductIntegrationResponseDto> getProductsByUserId(Long userId) throws ProductNotFoundException {
        try {
            List<GetProductResponseDto> responseDtos = Arrays.asList(Objects.requireNonNull(productsClient.getForObject(
                    "/api/products/user/{userId}",
                    GetProductResponseDto[].class,
                    userId
            )));
            return responseDtos
                    .stream()
                    .map(getProductResponseDto -> new GetProductIntegrationResponseDto(
                            getProductResponseDto.id(),
                            getProductResponseDto.productType(),
                            getProductResponseDto.balance()
                    ))
                    .toList();
        } catch (HttpClientErrorException ex) {
            throw new ProductNotFoundException("Products were not found");
        }
    }
}
