package com.locador.api.repository.basics;

import com.locador.api.model.basics.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
