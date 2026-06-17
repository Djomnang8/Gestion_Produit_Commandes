package com.bibliotheque.livre.controller;

import com.bibliotheque.livre.entity.Product;
import com.bibliotheque.livre.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) { this.repo = repo; }

    @GetMapping("/products")
    public List<Product> getAll() { return repo.findAll(); }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/products")
    public Product create(@RequestBody Product livre) { return repo.save(livre); }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return repo.findById(id).map(l -> {
            l.setNom(product.getNom());
            l.setPrix(product.getPrix());
            l.setQuantiteStock(product.getQuantiteStock());
            return ResponseEntity.ok(repo.save(l));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
