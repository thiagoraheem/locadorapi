package com.locador.api.controller.basics;

import com.locador.api.model.basics.ProductCategory;
import com.locador.api.service.basics.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productcategory")
@CrossOrigin
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping
    public ResponseEntity<List<ProductCategory>> getAll(){
        try{
           List<ProductCategory> categories = productCategoryService.findAll();
           if(categories.isEmpty()){
               return ResponseEntity.noContent().build();
           }
           return ResponseEntity.ok(categories);
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getById(@PathVariable Integer id) {
        return productCategoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductCategory> create(@RequestBody ProductCategory productCategory){
        try {
            return ResponseEntity.ok(productCategoryService.save(productCategory));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> update(@PathVariable Integer id, @RequestBody ProductCategory productCategory) {
        try {
            return ResponseEntity.ok(productCategoryService.update(id, productCategory));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
      try{
          productCategoryService.delete(id);
          return ResponseEntity.noContent().build();
      } catch(RuntimeException e){
          return ResponseEntity.notFound().build();
        }
    }
    }