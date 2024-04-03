package com.medicalstore.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medicalstore.entity.Medicine;
import com.medicalstore.entity.Stock;
import com.medicalstore.exception.ResourceNotFoundException;
import com.medicalstore.repository.MedicineRepository;
import com.medicalstore.service.MedicineService;
import com.medicalstore.service.StockService;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    private StockService stockService;
    @Autowired
    private MedicineService medicineService;
    @Autowired
    private MedicineRepository medicineRepository;

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStock() {
        List<Stock> stocks = stockService.getAllStock();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        Stock stock = stockService.getStockById(id);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

//    @PostMapping("medicine/{id}")
//    public ResponseEntity<Stock> addStock(@RequestBody Stock stock,@PathVariable Long id) {
//        Stock newStock = stockService.saveStock(stock);
//        Medicine med=medicineService.getMedicineById(id);
//        stockService.saveStock(newStock);
//        return new ResponseEntity<>(newStock, HttpStatus.CREATED);
////    }
//    @PostMapping("medicine/{id}")
//    public ResponseEntity<Stock> addStock(@RequestBody Stock stock, @RequestParam Long id) {
//        Medicine medicine = medicineRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Medicine not found with ID: " + id));
//        stock.setMedicine(medicine);
//        Stock newStock = stockService.saveStock(stock);
//        return new ResponseEntity<>(newStock, HttpStatus.CREATED);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStockById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@Valid @PathVariable Long id, @RequestBody Stock updatedStock) {
        Stock existingStock = stockService.getStockById(id);

        // Check if the medicine's quantity falls below the reorder level
        if (existingStock.getQuantity() < existingStock.getReorderLevel()) {
            // Update the stock with the new attributes
            existingStock.setQuantity(updatedStock.getQuantity());
            existingStock.setPurchasePrice(updatedStock.getPurchasePrice());
            existingStock.setPurchaseDate(updatedStock.getPurchaseDate());
            existingStock.setReorderLevel(updatedStock.getReorderLevel());
            existingStock.setLocation(updatedStock.getLocation());

            // Save the updated stock
            Stock updatedStockEntity = stockService.saveStock(existingStock);
            return new ResponseEntity<>(updatedStockEntity, HttpStatus.OK);
        } else{
            // Return a ResponseEntity with a 400 status code and a message indicating that the stock quantity is not below the reorder level
            return ResponseEntity.badRequest().body(existingStock);
    }
    }
        
        
        @GetMapping("/totalStockSpend")
        public ResponseEntity<Double> getTotalStockSpend(@RequestParam("startDate") String startDateStr,
                                                          @RequestParam("endDate") String endDateStr) {
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);

            double totalSpend = stockService.getTotalStockSpend(startDate, endDate);

            return new ResponseEntity<>(totalSpend, HttpStatus.OK);
        }

    
}

