package org.payment.service;

import lombok.RequiredArgsConstructor;
import org.payment.dto.GetProductIntegrationResponseDto;
import org.payment.dto.GetProductResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductIntegrationService {
    private final RestTemplate productsClient;

    public GetProductIntegrationResponseDto getProductById(Long productId) {
        GetProductResponseDto responseDto = productsClient.getForObject(
                    "/api/products/{productId}",
                    GetProductResponseDto.class,
                    productId
        );
        assert responseDto != null;
        return new GetProductIntegrationResponseDto(
                     responseDto.id(),
                    responseDto.productType(),
                    responseDto.balance()
        );
    }

    public List<GetProductIntegrationResponseDto> getProductsByUserId(Long userId) {
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
    }
}
