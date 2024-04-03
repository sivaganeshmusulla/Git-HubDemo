package com.medicalstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicalstore.entity.Admin;
import com.medicalstore.exception.AdminNotFoundException;
import com.medicalstore.exception.ResourceNotFoundException;
import com.medicalstore.repository.AdminRepository;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                              .orElseThrow(() -> new AdminNotFoundException("Admin not found with ID: " + id));
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public void deleteAdminById(Long id) {
        adminRepository.deleteById(id);
    }
}

