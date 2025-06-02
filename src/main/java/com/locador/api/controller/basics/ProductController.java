package com.locador.api.controller.basics;

import com.locador.api.dto.basics.ProductRequest;
import com.locador.api.dto.basics.ProductResponse;
import com.locador.api.service.basics.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        try {
            List<ProductResponse> productResponse = productService.findAll();
        if (productResponse.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productResponse);
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable @Positive Integer id){
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest productRequest){
        try{
            return ResponseEntity.ok(productService.save(productRequest));
        } catch(RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable @Positive Integer id, @RequestBody ProductRequest productRequest){
        try{
            return ResponseEntity.ok(productService.update(id, productRequest));
        }catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/{status}")
    public ResponseEntity<ProductResponse> updateStatus(@PathVariable Integer id,@PathVariable String status){
        try{
            return ResponseEntity.ok(productService.updateStatus(id, status));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        try{
            productService.delete(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
