package com.locador.api.repository.basics;

import com.locador.api.model.basics.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
