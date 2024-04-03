package com.medicalstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.medicalstore.entity.Admin;
import com.medicalstore.entity.Client;
import com.medicalstore.entity.Medicine;

@SpringBootApplication
public class MedicalStoreManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalStoreManagementSystemApplication.class, args);
		
	}

}
