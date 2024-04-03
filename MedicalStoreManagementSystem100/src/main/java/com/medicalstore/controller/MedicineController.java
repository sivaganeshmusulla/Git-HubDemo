package com.medicalstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medicalstore.entity.Client;
import com.medicalstore.entity.Medicine;
import com.medicalstore.entity.Sales;
import com.medicalstore.service.ClientService;
import com.medicalstore.service.MedicineService;
import com.medicalstore.service.SalesService;
import com.medicalstore.service.StockService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicines")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;
    @Autowired
    private ClientService clientService;

    

    @Autowired
    private SalesService salesService;
    
   

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
        Medicine medicine = medicineService.getMedicineById(id);
        return new ResponseEntity<>(medicine, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Medicine> addMedicine(@Valid @RequestBody Medicine medicine) {
        Medicine newMedicine = medicineService.saveMedicine(medicine);
        return new ResponseEntity<>(newMedicine, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@Valid @PathVariable Long id, @RequestBody Medicine updatedMedicine) {
        Medicine existingMedicine = medicineService.getMedicineById(id);
        
        existingMedicine.setName(updatedMedicine.getName());
        existingMedicine.setDosage(updatedMedicine.getDosage());
        existingMedicine.setPrice(updatedMedicine.getPrice());
        existingMedicine.setExpirationDate(updatedMedicine.getExpirationDate());
        existingMedicine.setManufacturer(updatedMedicine.getManufacturer());
        existingMedicine.setBatchNumber(updatedMedicine.getBatchNumber());
        existingMedicine.setManufacturingDate(updatedMedicine.getManufacturingDate());
        existingMedicine.setDescription(updatedMedicine.getDescription());
        existingMedicine.setStock(updatedMedicine.getStock());
        
        Medicine updatedMedicineEntity = medicineService.saveMedicine(existingMedicine);
        return new ResponseEntity<>(updatedMedicineEntity, HttpStatus.OK);
    }
    @GetMapping("/medicinesByClients")
    public ResponseEntity<Map<Client, List<Medicine>>> getMedicinesByClients(@RequestParam List<Long> clientIds) {
        Map<Client, List<Medicine>> medicinesByClients = new HashMap<>();
        for (Long clientId : clientIds) {
            Client client = clientService.getClientById(clientId);
            List<Sales> sales = salesService.getAllSales();
            List<Medicine> medicinesBought = sales.stream()
                    .filter(sale -> {
                        Client saleClient = sale.getClient();
                        return saleClient != null && saleClient.getId().equals(clientId);
                    })
                    .flatMap(sale -> sale.getMedicines().stream())
                    .collect(Collectors.toList());
            medicinesByClients.put(client, medicinesBought);
        }
        return new ResponseEntity<>(medicinesByClients, HttpStatus.OK);
    }
    

    
}

