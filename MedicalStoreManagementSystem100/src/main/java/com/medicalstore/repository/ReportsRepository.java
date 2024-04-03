package com.medicalstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicalstore.entity.Reports;

@Repository
	public interface ReportsRepository extends JpaRepository<Reports, Long> {
	    
	}

