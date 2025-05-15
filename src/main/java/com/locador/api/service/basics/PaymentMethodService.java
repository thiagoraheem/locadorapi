package com.locador.api.service.basics;

import com.locador.api.model.basics.PaymentMethod;
import com.locador.api.repository.basics.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethod> findAll(){
        return paymentMethodRepository.findAll();
    }

    public Optional<PaymentMethod> findById(Integer id){
        return paymentMethodRepository.findById(id);
        }

        public PaymentMethod save(PaymentMethod paymentMethod){
        return paymentMethodRepository.save(paymentMethod);
        }

        public PaymentMethod update(PaymentMethod paymentMethod, Integer id){
        if(!paymentMethodRepository.existsById(id)) {
            throw new RuntimeException("Método de pagamento não encontrado");
        }
        paymentMethod.setId(id);
        return paymentMethodRepository.save(paymentMethod);
        }

        public void delete(Integer id){
        if(!paymentMethodRepository.existsById(id)){
            throw new RuntimeException("Método de pagamento não encontrado");
        }
        paymentMethodRepository.deleteById(id);
        }
    }
