package org.example.repository;

import org.example.model.Product;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByUserId(Long userId);
    Optional<Product> findProductById(Long productId);
    void deleteProductsByUser(User user);
}
