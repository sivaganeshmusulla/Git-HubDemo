package com.medicalstore.controller;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.medicalstore.entity.Medicine;
import com.medicalstore.entity.Sales;
import com.medicalstore.service.SalesService;


@RestController
@RequestMapping("/reports")
public class ReportsController {
	
    @Autowired
    private SalesService salesService;
    
    @GetMapping("/sales/{id}/medicines")
    public ResponseEntity<List<Medicine>> getMedicinesSoldBySalesId(@PathVariable Long id) {
        Sales sales = salesService.getSaleById(id);
        List<Medicine> medicines = sales.getMedicines();
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }
    @GetMapping("/totalPrice")
    public ResponseEntity<Double> getTotalPriceBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        double totalPrice = salesService.getTotalPriceBetweenDates(startDate, endDate);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }
    @GetMapping("/sales/date/{date}")
    public ResponseEntity<List<Sales>> getSalesByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Sales> sales = salesService.getSalesByDate(date);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }
    
	
	

}
