package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bo.EmployeeBo;
import com.example.demo.data.EmployeeData;
import com.example.demo.service.EmployeeServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/employee")
@Slf4j
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl eserv;
	
//	@PostMapping("/create")
//	public ResponseEntity<?> addEmployeeDetails(@RequestBody EmployeeData empl){
//		eserv.createEmployee(empl);
//		log.info("Request Resived to add Employee details");
//		return ResponseEntity.ok("Employee added sucessfully");
//	}
//	@GetMapping("/all")
//	public List<EmployeeData> getAllEmployee(){
//		log.info("Request Resived to get all Employee details");
//		return eserv.getAllEmployee();
//	}
//	@GetMapping("/retrieval1")
//	public Optional<EmployeeData> getById1(@RequestParam("id") int id) {
//		log.info("Request Resived to get Employee details with id: {}",id);
//		return eserv.getEmployeebyId(id);	
//	}
//	@GetMapping("/retrieval/{id}")
//	public Optional<EmployeeData> getById(@PathVariable("id") int id) {
//		return eserv.getEmployeebyId(id);	
//	}
	
	@PostMapping("/create")
	public ResponseEntity<?> addEmployeeDetails(@RequestBody EmployeeBo empl){
		eserv.createEmployee(empl);
		log.info("Request Resived to add Employee details");
		return ResponseEntity.ok("Employee added sucessfully");
	}
	
	@GetMapping("/retrieval/{id}")
	public ResponseEntity<EmployeeBo> getById(@PathVariable("id") int id) {
		log.info("Request Resived to get Employee details with id: {}",id);
		EmployeeBo ebo = eserv.getEmployeebyId(id);	
		return ResponseEntity.ok(ebo);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<EmployeeBo>> getAllEmployee(){
		log.info("Request Resived to get all Employee details");
		List<EmployeeBo> ebo = eserv.getAllEmployee();
		return ResponseEntity.ok(ebo);
	}

}
