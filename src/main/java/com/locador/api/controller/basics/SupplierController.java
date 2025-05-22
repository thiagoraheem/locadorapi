package com.locador.api.controller.basics;

import com.locador.api.model.basics.Supplier;
import com.locador.api.service.basics.SupplierService;
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
    public ResponseEntity<List<Supplier>> getAll(){
        try{
            List<Supplier> suppliers = supplierService.findAll();
            if(suppliers.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(suppliers);
        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getById(@PathVariable Integer id){
        return supplierService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Supplier> create(@RequestBody Supplier supplier){
        try {
            return ResponseEntity.ok(supplierService.save(supplier));
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> update(@PathVariable Integer id, @RequestBody Supplier supplier){
        try{
            return ResponseEntity.ok(supplierService.update(id, supplier));
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
