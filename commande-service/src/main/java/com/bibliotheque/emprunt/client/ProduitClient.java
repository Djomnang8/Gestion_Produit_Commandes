package com.bibliotheque.emprunt.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "produit-service")
public interface ProduitClient {

    @GetMapping("/api/products/{id}")
    ProductDto getProductById(@PathVariable("id") Long id);

    @PutMapping("/api/products/{id}")
    void updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto product);
}
