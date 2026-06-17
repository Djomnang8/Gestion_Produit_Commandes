package com.bibliotheque.emprunt.controller;

import com.bibliotheque.emprunt.client.ProduitClient;
import com.bibliotheque.emprunt.entity.Commande;
import com.bibliotheque.emprunt.repository.CommandeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
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

    @GetMapping
    public List<Commande> getAll() { return repo.findAll(); }

    @GetMapping("/commandes/{id}")
    public ResponseEntity<Commande> getProductById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/commandes")
    public ResponseEntity<?> commander(@RequestBody Map<String, Object> body) {
        Long productId = Long.valueOf(body.get("productId").toString());
        Double quantite = Double.valueOf(body.get("quantite").toString());
        LocalDate date = LocalDate.parse(body.get("date").toString());

        Commande commande = new Commande();
        commande.setProductId(productId);
        commande.setQuantite(commande.getQuantite());
        commande.setDate(LocalDate.now());
        commande.setStatut(Commande.Statut.EN_COURS);
        repo.save(commande);

        return ResponseEntity.ok(commande);
    }

    @PutMapping("/{id}/retour")
    public ResponseEntity<?> updateProduct(@PathVariable Long id) {
        return repo.findById(id).map(e -> {
            e.setDate(LocalDate.now());
            e.setStatut(Commande.Statut.SUCESS);
            repo.save(e);
            return ResponseEntity.ok(e);
        }).orElse(ResponseEntity.notFound().build());
    }
}
