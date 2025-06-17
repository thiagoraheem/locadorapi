package com.locador.api.service.basics;

import com.locador.api.model.basics.ProductSerial;
import com.locador.api.repository.basics.ProductSerialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSerialService {
    @Autowired
    private ProductSerialRepository productSerialRepository;

    public List<ProductSerial> findAll(){
        return productSerialRepository.findAll();
    }

    public Optional<ProductSerial> findById(Integer id){
        return productSerialRepository.findById(id);
    }

    public ProductSerial save(ProductSerial productSerial){
        return productSerialRepository.save(productSerial);
    }

    public ProductSerial update(Integer id,ProductSerial productSerial ){
        if(!productSerialRepository.existsById(id)){
            throw new RuntimeException("ProductSerial não encontrado");
        }
        productSerial.setId(id);
        return productSerialRepository.save(productSerial);
    }

    public void delete(Integer id){
        if(!productSerialRepository.existsById(id)){
            throw new RuntimeException("ProductSerial não encontrado");
        }
        productSerialRepository.deleteById(id);
    }
}
