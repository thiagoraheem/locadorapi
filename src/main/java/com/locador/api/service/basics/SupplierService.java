package com.locador.api.service.basics;

import com.locador.api.dto.basics.ProductResponse;
import com.locador.api.dto.basics.SupplierRequest;
import com.locador.api.dto.basics.SupplierResponse;
import com.locador.api.model.basics.Product;
import com.locador.api.model.basics.Supplier;
import com.locador.api.repository.basics.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<SupplierResponse> findAll(){
        List<Supplier> supplier = supplierRepository.findAll();
        List<SupplierResponse> supplierResponses = new ArrayList<>();
        for(int i = 0; i < supplier.size(); i++){
            supplierResponses.add(new SupplierResponse(supplier.get(i)));
        }
        return supplierResponses;
    }
    public Optional<SupplierResponse> findById(Integer id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.map(SupplierResponse::new);
    }
    public SupplierResponse save(SupplierRequest supplierRequest){
        Supplier supplier = new Supplier(supplierRequest);
        supplierRepository.save(supplier);
        return new SupplierResponse(supplier);
    }

    public SupplierResponse update(Integer id, SupplierRequest supplierRequest){
        if(!supplierRepository.existsById(id)) {
            throw new RuntimeException("Supplier não encontrado");
        }
        Supplier supplier = new Supplier(supplierRequest);
        supplier.setId(id);
        supplierRepository.save(supplier);
        return new SupplierResponse(supplier);
    }

    public void delete(Integer id){
        if (!supplierRepository.existsById(id)){
            throw new RuntimeException("supplier não encontrado");
        }
        supplierRepository.deleteById(id);
    }
}
