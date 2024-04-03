package com.medicalstore.controller;

import com.medicalstore.entity.Client;
import com.medicalstore.entity.Medicine;
import com.medicalstore.entity.Sales;
import com.medicalstore.entity.Stock;
import com.medicalstore.service.ClientService;
import com.medicalstore.service.MedicineService;
import com.medicalstore.service.SalesService;
import com.medicalstore.service.StockService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private StockService stockService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private SalesService salesService;

    @GetMapping
    public ResponseEntity<List<Sales>> getAllSales() {
        List<Sales> sales = salesService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sales> getSaleById(@PathVariable Long id) {
        Sales sale = salesService.getSaleById(id);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @PostMapping("/purchaseMedicine")
    public ResponseEntity<Sales> purchaseMedicine(@Valid @RequestParam("clientId") Long clientId,
                                                   @RequestParam("medicineIds") List<Long> medicineIds,
                                                   @RequestParam("quantitiesSold") List<Long> quantitiesSold) {
        Sales newSales = salesService.purchaseMedicine(clientId, medicineIds, quantitiesSold);
        return new ResponseEntity<>(newSales, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        salesService.deleteSaleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/salesByClient/{clientId}")
    public ResponseEntity<List<Sales>> getSalesByClient(@PathVariable Long clientId) {
        List<Sales> sales = salesService.getSalesByClientId(clientId);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

}
