package com.locador.api.service.basics;

import com.locador.api.model.basics.ProductVehicle;
import com.locador.api.repository.basics.ProductVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductVehicleService {
    @Autowired
    private ProductVehicleRepository productVehicleRepository;

    public List<ProductVehicle> findAll(){
        return productVehicleRepository.findAll();
    }

    public Optional<ProductVehicle> findById(Integer id){
        return productVehicleRepository.findById(id);
    }

    public ProductVehicle save(ProductVehicle productVehicle){
        return productVehicleRepository.save(productVehicle);
    }

    public ProductVehicle update(Integer id, ProductVehicle productVehicle){
        if(!productVehicleRepository.existsById(id)){
            throw new RuntimeException("ProductVehicle não encontrado");
        }
        productVehicle.setId(id);
        return productVehicleRepository.save(productVehicle);
    }

    public void delete(Integer id){
        if(!productVehicleRepository.existsById(id)){
            throw new RuntimeException("ProductVehicle não encontrado");
        }
        productVehicleRepository.deleteById(id);
    }
}
