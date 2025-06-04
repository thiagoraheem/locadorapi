package com.locador.api.service.basics;

import com.locador.api.dto.basics.PaymentMethodRequest;
import com.locador.api.dto.basics.PaymentMethodResponse;
import com.locador.api.model.basics.PaymentMethod;
import com.locador.api.repository.basics.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethodResponse> findAll(){
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();
        List<PaymentMethodResponse> responses = new ArrayList<>();
        for(int i = 0; i < paymentMethods.size(); i++){
            responses.add(new PaymentMethodResponse(paymentMethods.get(i)));
        }
        return responses;
    }

    public Optional<PaymentMethodResponse> findById(Integer id){
        Optional<PaymentMethod> paymentMethod = paymentMethodRepository.findById(id);
        return paymentMethod.map(PaymentMethodResponse::new);
    }

    public PaymentMethodResponse save(PaymentMethodRequest request){
        PaymentMethod paymentMethod = new PaymentMethod(request);
        paymentMethodRepository.save(paymentMethod);
        return new PaymentMethodResponse(paymentMethod);
    }

    public PaymentMethodResponse update(Integer id, PaymentMethodRequest request){
        if(!paymentMethodRepository.existsById(id)){
            throw new RuntimeException("Método de pagamento não encontrado");
        }
        PaymentMethod paymentMethod = new PaymentMethod(request);
        paymentMethod.setId(id);
        paymentMethodRepository.save(paymentMethod);
        return new PaymentMethodResponse(paymentMethod);
    }

    public void delete(Integer id){
        if(!paymentMethodRepository.existsById(id)){
            throw new RuntimeException("Método de pagamento não encontrado");
        }
        paymentMethodRepository.deleteById(id);
    }
}
