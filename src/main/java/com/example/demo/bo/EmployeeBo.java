package com.example.demo.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.data.EmployeeData;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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


