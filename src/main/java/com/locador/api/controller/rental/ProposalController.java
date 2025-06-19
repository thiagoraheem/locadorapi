package com.locador.api.controller.rental;

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
    public ResponseEntity<List<Proposal>> getAll() {
        try {
            List<Proposal> list = proposalService.findAll();
            if (list.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(list);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proposal> getById(@PathVariable Integer id) {
        return proposalService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Proposal> create(@RequestBody Proposal proposal) {
        return ResponseEntity.ok(proposalService.save(proposal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proposal> update(@PathVariable Integer id, @RequestBody Proposal proposal) {
        try {
            return ResponseEntity.ok(proposalService.update(id, proposal));
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
