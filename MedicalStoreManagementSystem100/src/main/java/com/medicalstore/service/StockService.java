package com.medicalstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicalstore.entity.Stock;
import com.medicalstore.exception.ResourceNotFoundException;
import com.medicalstore.exception.StockNotFoundException;
import com.medicalstore.repository.StockRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    public Stock getStockById(Long id) {
        return stockRepository.findById(id)
                              .orElseThrow(() -> new StockNotFoundException("Stock not found with ID: " + id));
    }

    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
   }

    public void deleteStockById(Long id) {
        stockRepository.deleteById(id);
    }
    public double getTotalStockSpend(LocalDate startDate, LocalDate endDate) {
        List<Stock> stocks = stockRepository.findByPurchaseDateBetween(startDate, endDate);
        double totalSpend = 0.0;
        for (Stock stock : stocks) {
            totalSpend += stock.getQuantity() * stock.getPurchasePrice();
        }
        return totalSpend;
    }
}

