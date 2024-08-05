package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.*;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.example.demo.UserMapper.UserMapper;
import com.example.demo.bo.EmployeeBo;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.data.EmployeeData;
import com.example.demo.service.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
class EmpoyeeServiceTest {
	
	    @Mock
	    private EmployeeDao edao;

	    @Mock
	    private UserMapper umapper;
	    
	    

	    @InjectMocks
	    private EmployeeServiceImpl eService;

	    private EmployeeData eData;
	    private EmployeeBo eBo;

	    @BeforeEach
	    void values() {
	        eData = new EmployeeData(2, "Harsha", "harsha@gmail.com", "Hyderabad", "1234567890");
	        eBo = new EmployeeBo(2, "Harsha", "harsha@gmail.com", "Hyderabad", "1234567890");
	    }


	    @Test
	    void employeeCreateTest() {
	        when(umapper.EmployeeBoToEmployee(any(EmployeeBo.class))).thenReturn(eData);
	        when(edao.save(any(EmployeeData.class))).thenReturn(eData);
	        when(umapper.EmployeeToEmployeeBo(any(EmployeeData.class))).thenReturn(eBo);

	        EmployeeBo result = eService.createEmployee(eBo);

	        assertEquals(eBo, result);
	    }
	    
	    @Test
	    void employeeTestById() {
	    	
	    	 when(edao.findById(anyInt())).thenReturn(Optional.of(eData));
	         when(umapper.EmployeeToEmployeeBo(any(EmployeeData.class))).thenReturn(eBo);

	         EmployeeBo result = eService.getEmployeebyId(2);

	         assertEquals(eBo, result);
	    }
	    
	    @Test
	    void employeeTestByIdNotFound() {
	    	
	    	 when(edao.findById(anyInt())).thenReturn(Optional.empty());
	         //when(umapper.EmployeeToEmployeeBo(any(EmployeeData.class))).thenReturn(eBo);

	         EmployeeBo result1 = eService.getEmployeebyId(5);

	         assertNull( result1);
	    }
	    
	    @Test
	    void testGetAllEmployee() {
	        List<EmployeeData> employeeDataList = Arrays.asList(eData);
	        List<EmployeeBo> employeeBoList = Arrays.asList(eBo);

	        when(edao.findAll()).thenReturn(employeeDataList);
	        when(umapper.EmployeeToEmployeeBo(any(EmployeeData.class))).thenReturn(eBo);

	        List<EmployeeBo> result = eService.getAllEmployee();

	        assertEquals(employeeBoList, result);
	    }
	    
	}