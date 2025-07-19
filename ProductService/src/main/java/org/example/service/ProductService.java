package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CreateProductRequestDto;
import org.example.dto.CreateProductResponseDto;
import org.example.dto.GetProductDto;
import org.example.exception.ProductNotFoundException;
import org.example.model.Product;
import org.example.model.User;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<GetProductDto> getProductsByUserId(Long userId) throws ProductNotFoundException {
        List<Product> products = productRepository.findProductsByUserId(userId);
        if (!products.isEmpty()) {
            return products.stream().map(product ->
                    new GetProductDto(
                            product.getId(),
                            product.getAccountNumber(),
                            product.getBalance(),
                            product.getProductType(),
                            product.getUser())
            ).toList();
        } else throw new ProductNotFoundException(String.format("Products for user with id %d were not found", userId));
    }

    public GetProductDto getProductById(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findProductById(productId)
                .orElseThrow(new ProductNotFoundException(String.format("Product with id %d was not found", productId)));
        return new GetProductDto(product.getId(), product.getAccountNumber(), product.getBalance(), product.getProductType(), product.getUser());
    }

    public CreateProductResponseDto saveProduct(CreateProductRequestDto requestDto) {
        Product product = productRepository.save(Optional.of(new Product()).map(p -> {
            p.setAccountNumber(requestDto.getAccountNumber());
            p.setProductType(requestDto.getProductType());
            p.setBalance(requestDto.getBalance());
            User user = userRepository.getUserByUsername(requestDto.getUsername()).orElseThrow(EntityNotFoundException::new);
            p.setUser(user);
            return p;
        }).orElseThrow());
        return new CreateProductResponseDto(
                product.getId(),
                product.getAccountNumber(),
                product.getProductType(),
                product.getBalance(),
                product.getUser().getId()
        );
    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findProductById(productId).orElseThrow(EntityNotFoundException::new);
        productRepository.delete(product);
    }

    public void deleteProductsByUserId(Long userId) {
        User user =  userRepository.getUserById(userId).orElseThrow(EntityNotFoundException::new);
        productRepository.deleteProductsByUser(user);
    }
}