package com.locador.api.repository.basics;

import com.locador.api.model.basics.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer > {
}
