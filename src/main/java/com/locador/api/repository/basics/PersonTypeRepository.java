package com.locador.api.repository.basics;

import com.locador.api.model.basics.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonTypeRepository extends JpaRepository<PersonType, Integer> {
}
