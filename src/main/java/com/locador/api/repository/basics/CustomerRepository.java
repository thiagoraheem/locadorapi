package com.locador.api.repository.basics;

import com.locador.api.model.basics.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
