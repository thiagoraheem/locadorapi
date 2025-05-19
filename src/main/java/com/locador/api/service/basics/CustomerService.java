package com.locador.api.service.basics;

import com.locador.api.model.basics.Customer;
import com.locador.api.repository.basics.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer update(Integer id, Customer customer){
        if(!customerRepository.existsById(id)){
            throw new RuntimeException("Cliente não encontrado");
        }
        customer.setId(id);
        return customerRepository.save(customer);
    }

    public void delete(Integer id){
        if(!customerRepository.existsById(id)){
        throw new RuntimeException("Cliente não encontrado");
        }
        customerRepository.deleteById(id);
    }
}
