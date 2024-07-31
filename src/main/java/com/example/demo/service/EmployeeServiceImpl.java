package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.UserMapper.UserMapper;
import com.example.demo.bo.EmployeeBo;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.data.EmployeeData;
import com.example.demo.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDao edao;
	
	@Autowired
	private UserMapper umapper;

	@Override
	public EmployeeBo createEmployee(EmployeeBo empl) {
		
		log.info(Constants.ADDING_EMPLOYEE);
		EmployeeData edata = umapper.EmployeeBoToEmployee(empl);
		edata = edao.save(edata);
		return umapper.EmployeeToEmployeeBo(edata);
	}

	@Override
	public EmployeeBo getEmployeebyId(int empId) {
		
		log.info(Constants.RETRIEVING_EMPLOYEE);
		EmployeeData edata = edao.findById(empId).orElse(null);
		
		return umapper.EmployeeToEmployeeBo(edata);
	}

	@Override
	public List<EmployeeBo> getAllEmployee() {
		
		log.info(Constants.RETRIEVING_EMPLOYEE);
		
		return edao.findAll().stream()
				.map(umapper::EmployeeToEmployeeBo)
				.collect(Collectors.toList());
	}

//	@Override
//	public EmployeeData createEmployee(EmployeeData empl) {
//		// TODO Auto-generated method stub
//		log.info("Adding Employee data: {}",empl);
//		edao.save(empl);
//		return empl;
//	}
//
//	@Override
//	public Optional<EmployeeData> getEmployeebyId(int empId) {
//		log.info("Retrieving Employee data with id: {}", empId);
//		Optional<EmployeeData> em = edao.findById(empId);
//		return em;
//	}
//
//	@Override
//	public List<EmployeeData> getAllEmployee() {
//		// TODO Auto-generated method stub
//		log.info("Retrieving All Employees Data");
//		return edao.findAll();
//	}

}
