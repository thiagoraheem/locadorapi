package com.locador.api.repository.basics;

import com.locador.api.model.basics.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
}
