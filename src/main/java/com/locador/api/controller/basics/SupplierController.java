package com.locador.api.controller.basics;

import com.locador.api.dto.basics.SupplierRequest;
import com.locador.api.dto.basics.SupplierResponse;
import com.locador.api.model.basics.Supplier;
import com.locador.api.service.basics.SupplierService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAll(){
        try{
            List<SupplierResponse> suppliersResponse = supplierService.findAll();

            if(suppliersResponse.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(suppliersResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getById(@PathVariable @Positive Integer id){
        return supplierService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<SupplierResponse> create(@RequestBody SupplierRequest supplierRequest) {
        try {
            return ResponseEntity.ok(supplierService.save(supplierRequest));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> update(@PathVariable Integer id, @RequestBody SupplierRequest supplierRequest){
        try{
            return ResponseEntity.ok(supplierService.update(id, supplierRequest));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        try{
            supplierService.delete(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
