package com.locador.api.service.basics;

import com.locador.api.model.basics.Stock;
import com.locador.api.repository.basics.StockRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public List<Stock> findAll() {
       return stockRepository.findAll();
    }

    public Optional<Stock> findById(Integer id) {
        return stockRepository.findById(id);
    }

    public Stock save(Stock stock){
        return stockRepository.save(stock);
    }

    public Stock update(Integer id, Stock stock){
        if(!stockRepository.existsById(id)){
            throw new RuntimeException("Stock não encontrado");
        }
        stock.setId(id);
        return stockRepository.save(stock);
    }

    public void delete(Integer id){
        if(!stockRepository.existsById(id)){
            throw new RuntimeException("Stock não encontrado");
        }
        stockRepository.deleteById(id);
    }
}
