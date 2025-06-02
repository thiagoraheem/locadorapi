package com.locador.api.controller.basics;

import com.locador.api.dto.basics.StockRequest;
import com.locador.api.dto.basics.StockResponse;
import com.locador.api.service.basics.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<List<StockResponse>> getAll(){
        try{
            List<StockResponse> stockResponses = stockService.findAll();
            if(stockResponses.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(stockResponses);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponse> getById(@PathVariable Integer id){
        return stockService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StockResponse> create(@RequestBody StockRequest stockRequest){
        try{
            return ResponseEntity.ok(stockService.save(stockRequest));
        } catch(RuntimeException e){
         return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockResponse> update(@PathVariable Integer id, @RequestBody StockRequest stockRequest){
        try{
            return ResponseEntity.ok(stockService.update(id, stockRequest));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        try{
            stockService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
