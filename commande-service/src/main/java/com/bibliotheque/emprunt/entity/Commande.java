package com.bibliotheque.emprunt.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Commande {

    public enum Statut { SUCESS, EN_COURS }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int quantite;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Statut statut;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }
}
