package com.locador.api.repository.basics;

import com.locador.api.model.basics.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
