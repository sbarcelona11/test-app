package org.utec.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.utec.productservice.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserId(Long userId);
}
