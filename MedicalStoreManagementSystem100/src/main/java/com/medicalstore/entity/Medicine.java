package com.medicalstore.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;


@Entity
@Table(name="med")
public class Medicine {

    public Medicine() {
        super();
    }

    public Medicine(Long id, String name, String dosage, double price, LocalDate expirationDate, String manufacturer,
                    String batchNumber, LocalDate manufacturingDate, String description, Stock stock) {
        super();
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.price = price;
        this.expirationDate = expirationDate;
        this.manufacturer = manufacturer;
        this.batchNumber = batchNumber;
        this.manufacturingDate = manufacturingDate;
        this.description = description;
        this.stock = stock;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Dosage is required")
    private String dosage;

    @Positive(message = "Price must be positive")
    private double price;

    @NotNull(message = "Expiration date is required")
    @Future(message = "Expiration date must be in the future")
    private LocalDate expirationDate;

    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;

    @NotBlank(message = "Batch number is required")
    private String batchNumber;

    @NotNull(message = "Manufacturing date is required")
    @PastOrPresent(message = "Manufacturing date must be in the past or present")
    private LocalDate manufacturingDate;
    @NotBlank(message = "Description of Medicine  is required")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Stock stock;

    // Getters and setters
    

    @Override
    public String toString() {
        return "Medicine [id=" + id + ", name=" + name + ", dosage=" + dosage + ", price=" + price + ", expirationDate="
                + expirationDate + ", manufacturer=" + manufacturer + ", batchNumber=" + batchNumber + ", manufacturingDate=" + manufacturingDate + ", description="
                + description + ", stock=" + stock + "]";
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public LocalDate getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(LocalDate manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
}
