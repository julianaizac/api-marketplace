package com.facens.apimarketplace.infrastructure.repository;

import com.facens.apimarketplace.domain.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {
}
