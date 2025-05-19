package com.locador.api.controller.basics;

import com.locador.api.model.basics.Product;
import com.locador.api.model.basics.ProductSerial;
import com.locador.api.service.basics.ProductSerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/productserial")
public class ProductSerialController {
    @Autowired
    private ProductSerialService productSerialService;

    @RequestMapping
    public ResponseEntity<List<ProductSerial>> getAll(){
        try{
            return ResponseEntity.ok(productSerialService.findAll());
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<ProductSerial> getById(@PathVariable Integer id){
        return productSerialService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductSerial> create(@RequestBody ProductSerial productSerial){
        try{
            return ResponseEntity.ok(productSerialService.save(productSerial));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductSerial> update(@PathVariable Integer id, @RequestBody ProductSerial productSerial){
        try{
            return ResponseEntity.ok(productSerialService.update(id, productSerial));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        try{
            productSerialService.delete(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

}
