package com.medicalstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicalstore.entity.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{

}
