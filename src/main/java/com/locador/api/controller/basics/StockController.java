package com.locador.api.controller.basics;

import com.locador.api.model.basics.Stock;
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

    @RequestMapping
    public ResponseEntity<List<Stock>> getAll(){
        try{
            List<Stock> stocks = stockService.findAll();
            if(stocks.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(stocks);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Stock> getById(Integer id){
        return stockService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Stock> create(@RequestBody Stock stock){
        try{
            return ResponseEntity.ok(stockService.save(stock));
        } catch(RuntimeException e){
         return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> update(@PathVariable Integer id, @RequestBody Stock stock){
        try{
            return ResponseEntity.ok(stockService.update(id, stock));
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
