package com.example.demo.bo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeBo {
	
	@NotNull
	private int empId;
	
	@NotNull
	@Size(min = 3, max = 30,message = "Name should br more than 3 and less than 30 leters")
	private String employeeName;
	
	@NotNull
	@Email
	private String employeeEmailId;
	
	private String employeeAddress;
	
	@NotNull
	@Size(min = 10, max = 10, message = "Phone number should be od 10 digits ")
	private String employeeNumber;

}
