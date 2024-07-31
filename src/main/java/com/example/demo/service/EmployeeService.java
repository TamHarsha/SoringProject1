package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.bo.EmployeeBo;
import com.example.demo.data.EmployeeData;

public interface EmployeeService {
	
	EmployeeBo createEmployee(EmployeeBo empl);
	
	EmployeeBo getEmployeebyId(int empId);
	
	List<EmployeeBo> getAllEmployee();
	
//	EmployeeData createEmployee(EmployeeData empl);
//	
//	Optional<EmployeeData> getEmployeebyId(int empId);
//	
//	List<EmployeeData> getAllEmployee();
	

}
