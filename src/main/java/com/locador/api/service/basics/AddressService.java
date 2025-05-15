package com.locador.api.service.basics;

import com.locador.api.model.basics.Address;
import com.locador.api.repository.basics.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public Optional<Address> findById(Integer id){
        return addressRepository.findById(id);
    }

    public Address save(Address address){
        return addressRepository.save(address);
    }

    public Address update(Integer id, Address address){
        if(!addressRepository.existsById(id)){
            throw new RuntimeException("Endereço não encontrado");
        }
        address.setId(id);
        return addressRepository.save(address);
    }

    public void delete(Integer id) {
        if(!addressRepository.existsById(id)){
            throw new RuntimeException("endereço não encontrado");
        }
        addressRepository.deleteById(id);
    }
}
