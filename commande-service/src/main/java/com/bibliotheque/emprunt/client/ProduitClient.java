package com.bibliotheque.emprunt.client;

import com.bibliotheque.emprunt.entity.Commande;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "commande-service")
public interface ProduitClient {

        @PutMapping("/api/products/{id}")
        void updateProduct(@PathVariable("id") Long produitId, @RequestBody Commande commande);

        @PostMapping("/api/products")
        void updateProduct(@RequestBody Commande commande);


}
