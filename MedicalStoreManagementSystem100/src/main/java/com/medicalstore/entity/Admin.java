package com.medicalstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;



@Entity
@Table(name="Admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Pattern(regexp = "[a-zA-Z]{3,20}$" ,message = "Username must be between 4 and 20 characters")
    private String username;

   
    @Pattern(regexp = "^[A-Z]+[a-zA-Z0-9@#$%^&*!]{6,20}$", message = "Password must be at least 6 characters & Atleast One Capital Letter and one Symbol")
    private String password;

    

    public Admin() {
        super();
    }

    public Admin(Long id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
       
    }
    
    // Getters and setters

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	@Override
    public String toString() {
        return "Admin [id=" + id + ", username=" + username + ", password=" + password + "]";
    }
}
