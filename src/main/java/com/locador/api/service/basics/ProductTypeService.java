package com.locador.api.service.basics;

import com.locador.api.model.basics.Product;
import com.locador.api.model.basics.ProductType;
import com.locador.api.repository.basics.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    public List<ProductType> findAll(){
        return productTypeRepository.findAll();
    }

    public Optional<ProductType> findById(Integer id){
        return productTypeRepository.findById(id);
    }

    public ProductType save(ProductType productType){
        return productTypeRepository.save(productType);
    }

    public ProductType update(Integer id, ProductType productType){
        if(!productTypeRepository.existsById(id)){
          throw new RuntimeException("ProductType não encontrado");
        }
        productType.setId(id);
        return productTypeRepository.save(productType);
    }

    public void delete(Integer id){
            if (!productTypeRepository.existsById(id)){
                throw new RuntimeException("ProductType não encotrado");
        }
        productTypeRepository.deleteById(id);
    }
}
