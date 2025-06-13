package org.utec.productservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.oauth2.jwt.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.utec.productservice.model.Product;
import org.utec.productservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Create product", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product, @AuthenticationPrincipal Jwt jwt) {
        Long userId = jwt.getClaim("userId");// 3
        if (userId == null) {
            return ResponseEntity.internalServerError().body("User ID not found in JWT token");
        }
        product.setUserId(userId);
        return ResponseEntity.ok(productService.save(product));
    }

    @Operation(summary = "Get products", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<Product>> list(@AuthenticationPrincipal Jwt jwt) {
        Long userId = jwt.getClaim("userId");
        return ResponseEntity.ok(productService.getProductsForUser(userId));
    }
}
