package com.miranda.chatop.repositories;

import com.miranda.chatop.model.entities.RentalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// fournit des méthodes CRUD (Create, Read, Update, Delete) de base pour interagir avec la base de données.

public interface RentalRepository extends JpaRepository<RentalsEntity, Long> {
    public RentalsEntity findByName(String name);
}
