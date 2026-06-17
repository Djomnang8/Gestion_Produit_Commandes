package com.bibliotheque.livre.repository;

import com.bibliotheque.livre.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
