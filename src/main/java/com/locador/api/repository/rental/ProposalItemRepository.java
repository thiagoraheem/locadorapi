package com.locador.api.repository.rental;

import com.locador.api.model.rental.ProposalItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalItemRepository extends JpaRepository<ProposalItem, Integer> {
}
