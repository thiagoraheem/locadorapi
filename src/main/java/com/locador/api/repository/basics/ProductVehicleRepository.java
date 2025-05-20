package com.locador.api.repository.basics;

import com.locador.api.model.basics.ProductVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVehicleRepository extends JpaRepository<ProductVehicle, Integer> {
}
