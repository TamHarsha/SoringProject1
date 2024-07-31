package com.example.demo.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private int empId;
	
	@Column(name = "Name")
	private String employeeName;
    
	@Column(name = "EmailId")
	private String employeeEmailId;
	@Column(name = "Address")
	private String employeeAddress;
	

	@Column(name = "Phone Number")
	private String employeeNumber;

}
