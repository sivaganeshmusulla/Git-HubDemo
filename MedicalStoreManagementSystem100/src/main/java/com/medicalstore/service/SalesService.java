package com.medicalstore.service;

import com.medicalstore.entity.Client;
import com.medicalstore.entity.Medicine;
import com.medicalstore.entity.Sales;
import com.medicalstore.entity.Stock;
import com.medicalstore.exception.ResourceNotFoundException;
import com.medicalstore.exception.SalesNotFoundException;
import com.medicalstore.repository.ClientRepository;
import com.medicalstore.repository.MedicineRepository;
import com.medicalstore.repository.SalesRepository;
import com.medicalstore.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SalesService {
    @Autowired
    private SalesRepository saleRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private StockRepository stockRepository;

    public List<Sales> getAllSales() {
        return saleRepository.findAll();
    }

    public Sales getSaleById(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new SalesNotFoundException("Sale not found"));
    }

    public Sales purchaseMedicine(Long clientId, List<Long> medicineIds, List<Long> quantitiesSold) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        List<Medicine> medicines = medicineRepository.findAllById(medicineIds);

        if (medicines.size() != quantitiesSold.size()) {
            throw new IllegalArgumentException("Medicines and quantities sold lists must have the same size");
        }

        double totalPrice = calculateTotalPrice(medicines, quantitiesSold);

        for (int i = 0; i < medicines.size(); i++) {
            Medicine medicine = medicines.get(i);
            Long quantitySold = quantitiesSold.get(i);

            Stock stock = medicine.getStock();
            if (stock.getQuantity() < quantitySold) {
                throw new RuntimeException("Not enough stock for medicine: " + medicine.getName());
            }

            stock.setQuantity((int) (stock.getQuantity() - quantitySold));
            stockRepository.save(stock);
        }

        Sales sales = new Sales();
        sales.setClient(client);
        sales.setMedicines(medicines);
        sales.setTotalPrice(totalPrice);
        sales.setDate(LocalDate.now());

        return saleRepository.save(sales);
    }

    private double calculateTotalPrice(List<Medicine> medicines, List<Long> quantitiesSold) {
        double totalPrice = 0.0;
        for (int i = 0; i < medicines.size(); i++) {
            Medicine medicine = medicines.get(i);
            Long quantitySold = quantitiesSold.get(i);
            totalPrice += medicine.getPrice() * quantitySold;
        }
        return totalPrice;
    }

    public void deleteSaleById(Long id) {
        saleRepository.deleteById(id);
    }

    public Sales saveSales(Sales sales) {
        return saleRepository.save(sales);
    }

    public List<Sales> getSalesByClientId(Long clientId) {
        return saleRepository.findByClientId(clientId);
    }

	public List<Sales> getSalesByDate(LocalDate date) {
		return saleRepository.findByDate(date);
	}
	public double getTotalPriceBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Sales> sales = saleRepository.findByDateBetween(startDate, endDate);
        double totalPrice = 0.0;
        for (Sales sale : sales) {
            totalPrice += sale.getTotalPrice();
        }
        return totalPrice;
    }

}
