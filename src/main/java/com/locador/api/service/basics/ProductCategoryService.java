package com.locador.api.service.basics;

import com.locador.api.model.basics.ProductCategory;
import com.locador.api.repository.basics.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> findAll(){
        return productCategoryRepository.findAll();
    }

    public Optional<ProductCategory> findById(Integer id){
        return productCategoryRepository.findById(id);
    }

    public ProductCategory save(ProductCategory productCategory){
        return productCategoryRepository.save(productCategory);
    }


    public ProductCategory update(Integer id, ProductCategory productCategory){
        if(!productCategoryRepository.existsById(id)){
            throw new RuntimeException("ProductCategory não encontrado");
        }
        productCategory.setId(id);
        return productCategoryRepository.save(productCategory);
    }

    public void delete(Integer id){
        if(!productCategoryRepository.existsById(id)){
            throw new RuntimeException("ProductCategory não encontrado");
        }
        productCategoryRepository.deleteById(id);
    }
}
