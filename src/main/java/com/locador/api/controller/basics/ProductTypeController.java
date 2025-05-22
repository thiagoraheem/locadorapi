package com.locador.api.controller.basics;

import com.locador.api.model.basics.ProductType;
import com.locador.api.service.basics.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producttype")
@CrossOrigin
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping
    public ResponseEntity<List<ProductType>> getAll() {
        try{
            List<ProductType> productTypes = productTypeService.findAll();
            if(productTypes.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(productTypes);
        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductType> getById(@PathVariable Integer id){
        return productTypeService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductType> create(@RequestBody ProductType productType){
        try {
            return ResponseEntity.ok(productTypeService.save(productType));
        }
        catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductType> update(@PathVariable Integer id, @RequestBody ProductType productType){
        try{
            return ResponseEntity.ok(productTypeService.update(id, productType));
        }
        catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        try{
            productTypeService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
