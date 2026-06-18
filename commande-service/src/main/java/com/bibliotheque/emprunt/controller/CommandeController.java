package com.bibliotheque.emprunt.controller;

import com.bibliotheque.emprunt.client.ProductDto;
import com.bibliotheque.emprunt.client.ProduitClient;
import com.bibliotheque.emprunt.entity.Commande;
import com.bibliotheque.emprunt.repository.CommandeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CommandeController {

    private final CommandeRepository repo;
    private final ProduitClient produitClient;

    public CommandeController(CommandeRepository repo, ProduitClient produitClient) {
        this.repo = repo;
        this.produitClient = produitClient;
    }

    @GetMapping("/commandes/{id}")
    public ResponseEntity<Commande> getById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/commandes")
    public ResponseEntity<?> commander(@RequestBody Map<String, Object> body) {
        Long productId = Long.valueOf(body.get("productId").toString());
        int quantite = Integer.parseInt(body.get("quantite").toString());

        ProductDto produit = produitClient.getProductById(productId);
        if (produit.getQuantiteStock() < quantite) {
            return ResponseEntity.badRequest().body("Stock insuffisant");
        }

        Commande commande = new Commande();
        commande.setProductId(productId);
        commande.setQuantite(quantite);
        commande.setDate(LocalDate.now());
        commande.setStatut(Commande.Statut.EN_COURS);
        repo.save(commande);

        produit.setQuantiteStock(produit.getQuantiteStock() - quantite);
        produitClient.updateProduct(productId, produit);

        return ResponseEntity.ok(commande);
    }
}
