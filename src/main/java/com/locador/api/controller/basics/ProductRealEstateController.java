package com.locador.api.controller.basics;

import com.locador.api.model.basics.ProductRealEstate;
import com.locador.api.service.basics.ProductRealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productrealestate")
@CrossOrigin
public class ProductRealEstateController {

    @Autowired
    private ProductRealEstateService productRealEstateService;

    @GetMapping
    public ResponseEntity<List<ProductRealEstate>> getAll() {
        try {
            List<ProductRealEstate> productRealEstates = productRealEstateService.findAll();
            if (productRealEstates.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(productRealEstates);
        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRealEstate> getById(@PathVariable Integer id) {
        return productRealEstateService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductRealEstate> create(@RequestBody ProductRealEstate productRealEstate) {
        try {
            return ResponseEntity.ok(productRealEstateService.save(productRealEstate));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductRealEstate> update(@PathVariable Integer id, @RequestBody ProductRealEstate productRealEstate) {
        try {
            return ResponseEntity.ok(productRealEstateService.update(id, productRealEstate));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            productRealEstateService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
