package com.locador.api.service.basics;

import com.locador.api.dto.basics.AddressRequest;
import com.locador.api.dto.basics.AddressResponse;
import com.locador.api.model.basics.Address;
import com.locador.api.repository.basics.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<AddressResponse> findAll(){
        List<Address> address = addressRepository.findAll();
        List<AddressResponse> addressResponses = new ArrayList<>();
        for(Address addr : address){
            addressResponses.add(new AddressResponse(addr));
        }
        return addressResponses;
    }

    public Optional<AddressResponse> findById(Integer id){
        Optional<Address> address = addressRepository.findById(id);
        return address.map(AddressResponse::new);
    }

    public AddressResponse save(AddressRequest addressRequest){
        Address address = new Address(addressRequest);
        addressRepository.save(address);
        return new AddressResponse(address);
    }

    public AddressResponse update(Integer id, AddressRequest addressRequest){
        if(!addressRepository.existsById(id)){
            throw new RuntimeException("Endereço não encontrado");
        }
        Address address = new Address(addressRequest);
        address.setId(id);
        addressRepository.save(address);
        return new AddressResponse(address);
    }

    public void delete(Integer id) {
        if(!addressRepository.existsById(id)){
            throw new RuntimeException("endereço não encontrado");
        }
        addressRepository.deleteById(id);
    }
}
