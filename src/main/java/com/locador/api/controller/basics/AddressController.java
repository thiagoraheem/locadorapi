package com.locador.api.controller.basics;

import com.locador.api.model.basics.Address;
import com.locador.api.model.basics.ProductVehicle;
import com.locador.api.service.basics.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAll(){
        try {
            List<Address> address = addressService.findAll();
            if (address.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(address);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Integer id){
        return addressService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
    @PostMapping
    public ResponseEntity<Address> create(@RequestBody Address address){
        return ResponseEntity.ok(addressService.save(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Integer id, @RequestBody Address address){
        try {
            return ResponseEntity.ok(addressService.update(id, address));
        }
        catch(RuntimeException e){
           return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        try{
            addressService.delete(id);
            return ResponseEntity.noContent().build();
         } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
