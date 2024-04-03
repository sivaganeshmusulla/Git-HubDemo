package com.medicalstore.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;


@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "Quantity must be a positive number")
    private int quantity;

    @Min(value = 0, message = "Purchase price must be a positive number")
    private double purchasePrice;

    @NotNull(message = "Purchase date is required")
    @PastOrPresent(message = "Purchase date must be in the past or present")
    private LocalDate purchaseDate;

    @Min(value = 0, message = "Reorder level must be a positive number")
    private int reorderLevel;

    @NotBlank(message = "Location is required")
    private String location;


    public Stock() {}

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Stock [id=" + id + ", quantity=" + quantity + ", purchasePrice=" + purchasePrice + ", purchaseDate="
                + purchaseDate + ", reorderLevel=" + reorderLevel + ", location=" + location + "]";
    }
}
