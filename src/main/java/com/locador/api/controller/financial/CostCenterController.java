package com.locador.api.controller.financial;

import com.locador.api.model.financial.CostCenter;
import com.locador.api.service.financial.CostCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/costcenter")
public class CostCenterController {
    @Autowired
    private CostCenterService costCenterService;

    @GetMapping
    public ResponseEntity<List<CostCenter>> getAll(){
        try{
            List<CostCenter> costCenters = costCenterService.findAll();
            if(costCenters.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(costCenters);
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostCenter> findById(@PathVariable Integer id) {
        return costCenterService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<CostCenter> create(@RequestBody CostCenter costCenter){
        try{
            return ResponseEntity.ok(costCenterService.save(costCenter));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<CostCenter> update(@PathVariable Integer id, @RequestBody CostCenter costCenter){
        try{
            return ResponseEntity.ok(costCenterService.update(id, costCenter));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        try{
            costCenterService.delete(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
