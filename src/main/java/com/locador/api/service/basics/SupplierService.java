package com.locador.api.service.basics;

import com.locador.api.model.basics.Supplier;
import com.locador.api.repository.basics.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> findAll(){
        return supplierRepository.findAll();
    }
    public Optional<Supplier> findById(Integer id) {
        return supplierRepository.findById(id);
    }
    public Supplier save(Supplier supplier){
        return supplierRepository.save(supplier);
    }
    public Supplier update(Integer id, Supplier supplier){
        if(!supplierRepository.existsById(id)){
            throw new RuntimeException("Supplier não encontrado");
        }
        supplier.setId(id);
        return supplierRepository.save(supplier);
    }
    public void delete(Integer id){
        if (!supplierRepository.existsById(id)){
            throw new RuntimeException("supplier não encontrado");
        }
        supplierRepository.deleteById(id);
    }
}
