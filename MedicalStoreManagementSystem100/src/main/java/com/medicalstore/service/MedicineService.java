package com.medicalstore.service;

import com.medicalstore.entity.*;
import com.medicalstore.exception.MedicineNotFoundException;
import com.medicalstore.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.medicalstore.repository.MedicineRepository;
import com.medicalstore.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private StockRepository stockRepository;
        
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id)
                                 .orElseThrow(() -> new MedicineNotFoundException("Medicine not found with ID: " + id));
    }  
    
    

    

	public Medicine saveMedicine(Medicine medicine) {
		 return medicineRepository.save(medicine);
	}

	public List<Medicine> getMedicinesByIds(List<Long> medicineIds) {
        List<Medicine> medicines = new ArrayList<>();
        for (Long id : medicineIds) {
            Medicine medicine = getMedicineById(id);
            if (medicine != null) {
                medicines.add(medicine);
            }
        }
        return medicines;
    }
}
