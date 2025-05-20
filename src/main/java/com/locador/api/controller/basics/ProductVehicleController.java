package com.locador.api.controller.basics;

import com.locador.api.model.basics.ProductVehicle;
import com.locador.api.service.basics.ProductVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productvehicle")
@CrossOrigin
public class ProductVehicleController {
    @Autowired
    private ProductVehicleService productVehicleService;

    @GetMapping
    public ResponseEntity<List<ProductVehicle>> getAll(){
        try {
            List<ProductVehicle> vehicles = productVehicleService.findAll();
            if (vehicles.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(vehicles);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductVehicle> getById(@PathVariable Integer id){
            return productVehicleService.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<ProductVehicle> create(@RequestBody ProductVehicle productVehicle){
        try{
            return ResponseEntity.ok(productVehicleService.save(productVehicle));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductVehicle> update(@PathVariable Integer id, @RequestBody ProductVehicle productVehicle){
        try{
            return ResponseEntity.ok(productVehicleService.update(id, productVehicle));
        }catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        try {
            productVehicleService.delete(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
