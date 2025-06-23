package com.locador.api.service.rental;

import com.locador.api.model.rental.Proposal;
import com.locador.api.repository.rental.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProposalService {

    @Autowired
    private ProposalRepository proposalRepository;

    public List<Proposal> findAll() {
        return proposalRepository.findAll();
    }

    public Optional<Proposal> findById(Integer id) {
        return proposalRepository.findById(id);
    }

    public Proposal save(Proposal proposal) {
        return proposalRepository.save(proposal);
    }

    public Proposal update(Integer id, Proposal proposal) {
        if (!proposalRepository.existsById(id)) {
            throw new RuntimeException("Proposta não encontrada");
        }
        proposal.setId(id);
        return proposalRepository.save(proposal);
    }

    public void delete(Integer id) {
        if (!proposalRepository.existsById(id)) {
            throw new RuntimeException("Proposta não encontrada");
        }
        proposalRepository.deleteById(id);
    }
}
