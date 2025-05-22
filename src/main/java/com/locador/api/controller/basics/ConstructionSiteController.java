package com.locador.api.controller.basics;

import com.locador.api.model.basics.ConstructionSite;
import com.locador.api.service.basics.ConstructionSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/constructionsite")
public class ConstructionSiteController {

    @Autowired
    private ConstructionSiteService constructionSiteService;

    @GetMapping
    public ResponseEntity<List<ConstructionSite>> getAll()
    {
        try{
            List<ConstructionSite> constructionSites = constructionSiteService.findAll();
            if(constructionSites.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(constructionSites);
        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConstructionSite> getById(@PathVariable Integer id) {
        return constructionSiteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ConstructionSite> create(@RequestBody ConstructionSite constructionSite) {
        return ResponseEntity.ok(constructionSiteService.save(constructionSite));
        }

    @PutMapping("/{id}")
    public ResponseEntity<ConstructionSite> update(@PathVariable Integer id, @RequestBody ConstructionSite constructionSite) {
        try {
            return ResponseEntity.ok(constructionSiteService.update(id, constructionSite));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            constructionSiteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
