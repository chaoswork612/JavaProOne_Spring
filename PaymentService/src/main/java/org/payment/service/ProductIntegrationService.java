package org.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.payment.dto.GetProductIntegrationResponseDto;
import org.payment.dto.GetProductResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
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
        List<GetProductResponseDto> responseDtos = Arrays.asList(productsClient.getForObject(
                    "/api/products/user/{userId}",
                    GetProductResponseDto[].class,
                    userId
        ));
        assert !responseDtos.isEmpty();
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
