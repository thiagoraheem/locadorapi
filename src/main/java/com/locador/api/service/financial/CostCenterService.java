package com.locador.api.service.financial;

import com.locador.api.model.financial.CostCenter;
import com.locador.api.repository.financial.CostCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostCenterService {
    @Autowired
    private CostCenterRepository costCenterRepository;

    public List<CostCenter> findAll(){
        return costCenterRepository.findAll();
    }
    public Optional<CostCenter> findById(Integer id){
        return costCenterRepository.findById(id);
    }
    public CostCenter save(CostCenter costCenter){
        return costCenterRepository.save(costCenter);
    }
    public CostCenter update(Integer id, CostCenter costCenter){
        if(!costCenterRepository.existsById(id)) {
            throw new RuntimeException("CostCenter não encontrado");
        }
        costCenter.setId(id);
        return costCenterRepository.save(costCenter);
    }
    public void delete(Integer id){
        if(!costCenterRepository.existsById(id)) {
            throw new RuntimeException("CostCenter não encontrado");
        }
        costCenterRepository.deleteById(id);
    }
}
