package com.example.demo.bo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeBo {
	
	@NotNull
	private int empId;
	
	@NotNull
	@Size(min = 3, max = 30)
	private String employeeName;
	
	private String employeeEmailId;
	
	private String employeeAddress;
	
	private String employeeNumber;

}
