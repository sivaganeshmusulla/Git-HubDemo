package com.medicalstore.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;


@Entity
@Table(name="Reports")
public class Reports {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @NotNull(message = "Date is required")
    @PastOrPresent
    (message = "Date must be in the past or present")
    private LocalDate date;
    
    @Positive(message = "Cost must be positive")
    private double cost;
    
    public Reports() {
    }

    public Reports(Long id, String description, LocalDate date, double cost) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.cost = cost;
    }

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
