package com.locador.api.controller.basics;

import com.locador.api.dto.basics.AddressRequest;
import com.locador.api.dto.basics.AddressResponse;
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
    public ResponseEntity<List<AddressResponse>> getAll(){
        try {
            List<AddressResponse> addressResponses = addressService.findAll();
            if (addressResponses.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(addressResponses);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getById(@PathVariable Integer id){
        return addressService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
    @PostMapping
    public ResponseEntity<AddressResponse> create(@RequestBody AddressRequest addressRequest){
        return ResponseEntity.ok(addressService.save(addressRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> update(@PathVariable Integer id, @RequestBody AddressRequest addressRequest){
        try {
            return ResponseEntity.ok(addressService.update(id, addressRequest));
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
