package com.locador.api.controller.basics;

import com.locador.api.model.basics.PaymentMethod;
import com.locador.api.service.basics.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/paymentmethod")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<List<PaymentMethod>> getAll(){
        try{
            List<PaymentMethod> paymentMethods = paymentMethodService.findAll();
            if(paymentMethods.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(paymentMethods);
        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> getById(@PathVariable Integer id) {
        return paymentMethodService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PaymentMethod> create(@RequestBody PaymentMethod paymentMethod){
        return ResponseEntity.ok(paymentMethodService.save(paymentMethod));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethod> update(@PathVariable Integer id, @RequestBody PaymentMethod paymentMethod) {
        try {
            return ResponseEntity.ok(paymentMethodService.update(paymentMethod, id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        try{
            paymentMethodService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
