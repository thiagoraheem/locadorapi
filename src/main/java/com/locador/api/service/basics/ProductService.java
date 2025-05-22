package com.locador.api.service.basics;

import com.locador.api.dto.ProductRequest;
import com.locador.api.model.basics.Product;
import com.locador.api.repository.basics.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Integer id){
        return productRepository.findById(id);
    }

    public ProductRequest save(ProductRequest product)
    {
        Product newProduct = new Product(product);

        newProduct = productRepository.save(newProduct);

        return new ProductRequest(newProduct);
    }

    public Product update(Integer id, Product product){
        if(!productRepository.existsById(id)){
            throw new RuntimeException("Produto não encontrado");
        }
        product.setId(id);
        return productRepository.save(product);
    }

    public void delete(Integer id){
        if(!productRepository.existsById(id)){
            throw new RuntimeException("Produto não encontrado");
        }
        productRepository.deleteById(id);
    }
}
