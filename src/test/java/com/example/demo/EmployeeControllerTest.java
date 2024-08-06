package com.example.demo;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.bo.EmployeeBo;
import com.example.demo.controller.EmployeeController;
import com.example.demo.service.EmployeeServiceImpl;

class EmployeeControllerTest {
	
	@Mock
	private EmployeeServiceImpl eServ;
	
	@InjectMocks
	private EmployeeController eController;
	
	   @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	@Test
	void testAddEmployeeDetails() {
		
		EmployeeBo eBo = new EmployeeBo(1,"Harsha","abc@gmail.com","Hyd","1234567890");
		
		when(eServ.createEmployee(any(EmployeeBo.class))).thenReturn(eBo);
		
		ResponseEntity<?> response = eController.addEmployeeDetails(eBo);
		System.out.println(response);
		
		assert response.getStatusCode() == HttpStatus.OK;
		assert response.getBody().equals("Employee added sucessfully");
	}

	@Test
	void testGetById() {
		int id = 1;
		EmployeeBo eBo = new EmployeeBo(id,"Harsha","abc@gmail.com","Hyd","1234567890");
		
		when(eServ.getEmployeebyId(id)).thenReturn(eBo);
		
		ResponseEntity<?> response = eController.getById(id);
		
		assert response.getStatusCode() == HttpStatus.OK;
		assert response.getBody().equals(eBo);
		
	}
	

	@Test
	void testGetAllEmployee() {
		
		List<EmployeeBo> employee = new ArrayList<EmployeeBo>();
		
		employee.add(new EmployeeBo(1,"Harsha","abc@gmail.com","Hyd","1234567890"));
		employee.add(new EmployeeBo(2,"Sai","abc@gmail.com","Hyd","1234567890"));
		
		when(eServ.getAllEmployee()).thenReturn(employee);
		
		ResponseEntity<?> response = eController.getAllEmployee();
		
		assert response.getStatusCode() == HttpStatus.OK;
		assert response.getBody().equals(employee);
				
		
	}
	

	@Test
	void testHealthCheck() {
		
		ResponseEntity<?> response = eController.healthCheck();
		
		assert response.getStatusCode() == HttpStatus.OK;
		assert response.getBody().equals("Service is up and running!");
	}
	
//	@Test
//	void testGetByIdNull() {
//		int id = 0;
//		//EmployeeBo eBo = new EmployeeBo(id,"Harsha","abc@gmail.com","Hyd","1234567890");
//		
//		when(eServ.getEmployeebyId(id)).thenReturn();
//		
//		ResponseEntity<?> response = eController.getById(id);
//		
//		assert response.getStatusCode() == HttpStatus.OK;
//		assert response.getBody().equals(eBo);
//		
//	}

}
