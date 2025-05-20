package com.locador.api.service.basics;

import com.locador.api.model.basics.ProductRealEstate;
import com.locador.api.repository.basics.ProductRealEstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductRealEstateService {


    @Autowired
    private ProductRealEstateRepository productRealEstateRepository;

    public List<ProductRealEstate> findAll(){
        return productRealEstateRepository.findAll();
    }

    public Optional<ProductRealEstate> findById(Integer id){
        return productRealEstateRepository.findById(id);
    }

    public ProductRealEstate save(ProductRealEstate productRealEstate){
        return productRealEstateRepository.save(productRealEstate);
    }

    public ProductRealEstate update(Integer id, ProductRealEstate productRealEstate){
        if(!productRealEstateRepository.existsById(id)){
            throw new RuntimeException("ProductRealEstate não encontrado");
        }
        productRealEstate.setId(id);
        return productRealEstateRepository.save(productRealEstate);
    }

    public void delete(Integer id){
        if(!productRealEstateRepository.existsById(id)){
            throw new RuntimeException("ProductRealEstate não encontrado");
        }
        productRealEstateRepository.deleteById(id);
    }
}
