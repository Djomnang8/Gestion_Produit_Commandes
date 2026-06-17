package com.bibliotheque.emprunt.repository;

import com.bibliotheque.emprunt.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

}
