package com.medicalstore.tests;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.medicalstore.entity.Admin;
import com.medicalstore.exception.ResourceNotFoundException;
import com.medicalstore.repository.AdminRepository;
import com.medicalstore.service.AdminService;

public class AdminTests {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin(1L, "admin1", "password1"));
        admins.add(new Admin(2L, "admin2", "password2"));

        when(adminRepository.findAll()).thenReturn(admins);

        List<Admin> result = adminService.getAllAdmins();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetAdminById() {
        Admin admin = new Admin(1L, "admin1", "password1");

        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        Admin result = adminService.getAdminById(1L);

        assertEquals(admin, result);
    }

    @Test
    public void testGetAdminById_WhenAdminNotFound() {
        when(adminRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            adminService.getAdminById(1L);
        });
    }

    @Test
    public void testSaveAdmin() {
        Admin admin = new Admin(1L, "admin1", "password1");

        when(adminRepository.save(admin)).thenReturn(admin);

        Admin result = adminService.saveAdmin(admin);

        assertEquals(admin, result);
    }

    @Test
    public void testDeleteAdminById() {
        doNothing().when(adminRepository).deleteById(1L);

        adminService.deleteAdminById(1L);

        verify(adminRepository, times(1)).deleteById(1L);
    }
}

