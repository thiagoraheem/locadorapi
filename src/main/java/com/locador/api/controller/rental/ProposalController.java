package com.locador.api.controller.rental;

import com.locador.api.dto.rental.ProposalRequest;
import com.locador.api.dto.rental.ProposalResponse;
import com.locador.api.model.rental.Proposal;
import com.locador.api.service.rental.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/proposal")
public class ProposalController {

    @Autowired
    private ProposalService proposalService;

    @GetMapping
    public ResponseEntity<List<ProposalResponse>> getAll() {
        try {
            List<ProposalResponse> proposalResponses = proposalService.findAll();
            if (proposalResponses.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(proposalResponses);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProposalResponse> getById(@PathVariable Integer id) {
        return proposalService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProposalResponse> create(@RequestBody ProposalRequest proposalRequest) {
        return ResponseEntity.ok(proposalService.save(proposalRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProposalResponse> update(@PathVariable Integer id, @RequestBody ProposalRequest proposalRequest) {
        try {
            return ResponseEntity.ok(proposalService.update(id, proposalRequest));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            proposalService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
