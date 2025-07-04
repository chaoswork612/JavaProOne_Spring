package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CreateProductRequestDto;
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

    public List<Product> getProductsByUserId(Long userId) {
        return productRepository.findProductsByUserId(userId);
    }

    public Product getProductById(Long productId) {
        return productRepository.findProductById(productId).orElseThrow(EntityNotFoundException::new);
    }

    public Product saveProduct(CreateProductRequestDto requestDto) {
        return productRepository.save(Optional.of(new Product()).map(p -> {
            p.setAccountNumber(requestDto.getAccountNumber());
            p.setProductType(requestDto.getProductType());
            p.setBalance(requestDto.getBalance());
            User user = userRepository.getUserByUsername(requestDto.getUsername()).orElseThrow(EntityNotFoundException::new);
            p.setUser(user);
            return p;
        }).orElseThrow(EntityNotFoundException::new));
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