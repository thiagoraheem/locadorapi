package com.locador.api.repository.basics;

import com.locador.api.model.basics.ProductSerial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSerialRepository extends JpaRepository<ProductSerial, Integer> {
}
