package com.locador.api.service.rental;

import com.locador.api.dto.rental.ProposalItemRequest;
import com.locador.api.dto.rental.ProposalRequest;
import com.locador.api.dto.rental.ProposalResponse;
import com.locador.api.enumeracoes.rental.ProposalStatus;
import com.locador.api.model.rental.Proposal;
import com.locador.api.model.rental.ProposalItem;
import com.locador.api.repository.basics.CustomerRepository;
import com.locador.api.repository.basics.ProductRepository;
import com.locador.api.repository.rental.ProposalItemRepository;
import com.locador.api.repository.rental.ProposalRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ProposalService {

    @Autowired
    private ProposalRepository proposalRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProposalItemRepository proposalItemRepository;
    private int totalAmount;
    private long days;

    public List<ProposalResponse> findAll() {
        List<Proposal> proposal = proposalRepository.findAll();
        return proposal.stream()
                .map(ProposalResponse::new)
                .toList();
    }

    public Optional<ProposalResponse> findById(Integer id) {
        Optional<Proposal> proposal = proposalRepository.findById(id);
        return proposal.map(ProposalResponse::new);
    }

    public ProposalResponse save(ProposalRequest proposalRequest) {
        Proposal proposal = new Proposal(proposalRequest);
        if (customerRepository.existsById(proposalRequest.getCustomerId())) {
            for (var itemRequest : proposalRequest.getProposalItemRequests()) {
                if (!productRepository.existsById(itemRequest.getProductId())) {
                    throw new RuntimeException("Produto com ID " + itemRequest.getProductId() + " não encontrado");
                }
            }
            proposal.setProposalStatus(ProposalStatus.PENDENTE);
            proposalRepository.save(proposal);

            for(int i = 0; i < proposalRequest.getProposalItemRequests().size(); i++) {
                ProposalItemRequest proposalItemRequest = proposalRequest.getProposalItemRequests().get(i);
                ProposalItem proposalItem = new ProposalItem();
                proposalItem.setProposalId(proposal.getId());//esse id ainda nao foi preenchido, pois ele é gerado automaticamente só depois que salvo essa proposta no banco. entao esse campo esta nulo?
                proposalItem.setProductId(proposalItemRequest.getProductId());
                proposalItem.setDescription(proposalItemRequest.getDescription());
                proposalItem.setQuantity(proposalItemRequest.getQuantity());
                proposalItem.setAmount(proposalItemRequest.getAmount());
                //Calcular totalAmount com base nos dias * quantidade * valor diário
                days = ChronoUnit.DAYS.between(proposalRequest.getStartDate(), proposalRequest.getEndDate());
                proposalItem.setTotalAmount(days * proposalItemRequest.getQuantity() * proposalItemRequest.getAmount());
                proposalItemRepository.save(proposalItem);
            }
        }
        return new ProposalResponse(proposal);
    }

    public ProposalResponse update(Integer id, ProposalRequest proposalRequest) {
        if (!proposalRepository.existsById(id)) {
            throw new RuntimeException("Proposta não encontrada");
        }
        Proposal proposal = new Proposal(proposalRequest);
        proposal.setId(id);
        proposalRepository.save(proposal);
        return new ProposalResponse(proposal);
    }

    public void delete(Integer id) {
        if (!proposalRepository.existsById(id)) {
            throw new RuntimeException("Proposta não encontrada");
        }
        proposalRepository.deleteById(id);
    }
}
