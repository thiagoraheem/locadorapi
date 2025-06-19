package com.locador.api.repository.rental;

import com.locador.api.model.rental.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal, Integer> {
}
