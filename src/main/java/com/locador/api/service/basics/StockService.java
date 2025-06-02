package com.locador.api.service.basics;

import com.locador.api.dto.basics.StockRequest;
import com.locador.api.dto.basics.StockResponse;
import com.locador.api.model.basics.Stock;
import com.locador.api.repository.basics.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public List<StockResponse> findAll() {
        List<Stock> stocks = stockRepository.findAll();
        List<StockResponse> stockResponses = new ArrayList<>();
        for(int i = 0; i < stocks.size(); i++){
            stockResponses.add(new StockResponse(stocks.get(i)));
        }
        return stockResponses;
    }

    public Optional<StockResponse> findById(Integer id) {
        Optional<Stock> stock = stockRepository.findById(id);
        return stock.map(StockResponse::new);
    }

    public StockResponse save(StockRequest stockRequest){
        Stock stock = new Stock(stockRequest);
        stockRepository.save(stock);
        return new StockResponse(stock);
    }

    public StockResponse update(Integer id, StockRequest stockRequest){
        if(!stockRepository.existsById(id)){
            throw new RuntimeException("Stock não encontrado");
        }
        Stock stock = new Stock(stockRequest);
        stock.setId(id);
        stockRepository.save(stock);
        return new StockResponse(stock);
    }

    public void delete(Integer id){
        if(!stockRepository.existsById(id)){
            throw new RuntimeException("Stock não encontrado");
        }
        stockRepository.deleteById(id);
    }
}
