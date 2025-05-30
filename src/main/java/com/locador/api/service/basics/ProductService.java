package com.locador.api.service.basics;

import com.locador.api.dto.basics.ProductRequest;
import com.locador.api.dto.basics.ProductResponse;
import com.locador.api.model.basics.Product;
import com.locador.api.repository.basics.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> responses = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            responses.add(new ProductResponse(products.get(i)));
        }
        return responses;
    }

    public Optional<ProductResponse> findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ProductResponse::new);
    }


    public ProductResponse save(ProductRequest productRequest) {
    Product product = new Product(productRequest);
    productRepository.save(product);
    return new ProductResponse(product);
    }

public ProductResponse update(Integer id, ProductRequest productRequest) {
    if (!productRepository.existsById(id))   {
        throw new RuntimeException("Produto não encontrado");
    }
    Product product = new Product(productRequest);
    product.setId(id);
    productRepository.save(product);
    return new ProductResponse(product);
}

    public ProductResponse updateStatus(Integer id, String status) {
        Product product = productRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        product.setStatus(status);
        productRepository.save(product);
        return new ProductResponse(product);
    }

public void delete(Integer id) {
    if (!productRepository.existsById(id)) {
        throw new RuntimeException("Produto não encontrado");
    }
    productRepository.deleteById(id);
}
}
