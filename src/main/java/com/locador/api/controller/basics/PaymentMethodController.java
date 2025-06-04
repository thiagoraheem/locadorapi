package com.locador.api.controller.basics;

import com.locador.api.dto.basics.PaymentMethodRequest;
import com.locador.api.dto.basics.PaymentMethodResponse;
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
    public ResponseEntity<List<PaymentMethodResponse>> getAll(){
        try {
            List<PaymentMethodResponse> paymentMethods = paymentMethodService.findAll();
            if(paymentMethods.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(paymentMethods);
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodResponse> getById(@PathVariable Integer id){
        try {
            return paymentMethodService.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PaymentMethodResponse> create(@RequestBody PaymentMethodRequest paymentMethodRequest){
        try {
            return ResponseEntity.ok(paymentMethodService.save(paymentMethodRequest));
        } catch(RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodResponse> update(@PathVariable Integer id, @RequestBody PaymentMethodRequest paymentMethodRequest){
        try {
            return ResponseEntity.ok(paymentMethodService.update(id, paymentMethodRequest));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        try {
            paymentMethodService.delete(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
