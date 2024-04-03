package com.medicalstore.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicalstore.entity.Sales;
@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {
	List<Sales> findByClientId(Long clientId);
	List<Sales> findByDate(LocalDate date);
	List<Sales> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
