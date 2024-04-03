package com.medicalstore.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medicalstore.entity.Client;
import com.medicalstore.entity.Medicine;
import com.medicalstore.entity.Sales;
import com.medicalstore.entity.Stock;
import com.medicalstore.service.ClientService;
import com.medicalstore.service.MedicineService;
import com.medicalstore.service.SalesService;
import com.medicalstore.service.StockService;


@RestController
@RequestMapping("/report")
public class GetAllReportController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private StockService stockService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private SalesService salesService;

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getAllStock() {
        List<Stock> stocks = stockService.getAllStock();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @GetMapping("/medicines")
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @GetMapping("/sales")
    public ResponseEntity<List<Sales>> getAllSales() {
        List<Sales> sales = salesService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }
   
    
    
  
   
    



}

