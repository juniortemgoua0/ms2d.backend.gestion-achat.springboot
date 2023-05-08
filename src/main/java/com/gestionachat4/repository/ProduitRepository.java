package com.gestionachat4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionachat4.entities.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit , Short > {}
