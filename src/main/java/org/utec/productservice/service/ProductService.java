package org.utec.productservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.utec.productservice.model.Product;
import org.utec.productservice.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProductsForUser(Long userId) {
        return productRepository.findByUserId(userId);
    }
}
