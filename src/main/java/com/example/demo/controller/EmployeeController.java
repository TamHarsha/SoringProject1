package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/employee")
@Slf4j
@Validated
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl eserv;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job employeeFromH2toCSV;
	
	@PostMapping("/create")
	public ResponseEntity<?> addEmployeeDetails(@Valid @RequestBody EmployeeBo empl){
		eserv.createEmployee(empl);
		log.info("Request Resived to add Employee details");
		return ResponseEntity.ok("Employee added sucessfully");
	}
	
	@GetMapping("/retrieval/{id}")
	public ResponseEntity<EmployeeBo> getById(@NotNull @PathVariable("id") int id) {
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
	
	@GetMapping("/health")
	public ResponseEntity<String> healthCheck() {
	    log.info("Health check requested");
	    return ResponseEntity.ok("Service is up and running!");
	}
	
	@GetMapping("/h2tocsv")
	public ResponseEntity<?> employeeH2toCSV() throws Exception{
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("startAt", System.currentTimeMillis())
				.toJobParameters();
		jobLauncher.run(employeeFromH2toCSV, jobParameters);
		
		return ResponseEntity.ok("Data from H2 to Csv is Done.");
	}
	  
	  

	  
//		@PostMapping("/create")
//		public ResponseEntity<?> addEmployeeDetails(@RequestBody EmployeeData empl){
//			eserv.createEmployee(empl);
//			log.info("Request Resived to add Employee details");
//			return ResponseEntity.ok("Employee added sucessfully");
//		}
//		@GetMapping("/all")
//		public List<EmployeeData> getAllEmployee(){
//			log.info("Request Resived to get all Employee details");
//			return eserv.getAllEmployee();
//		}
//		@GetMapping("/retrieval1")
//		public Optional<EmployeeData> getById1(@RequestParam("id") int id) {
//			log.info("Request Resived to get Employee details with id: {}",id);
//			return eserv.getEmployeebyId(id);	
//		}
//		@GetMapping("/retrieval/{id}")
//		public Optional<EmployeeData> getById(@PathVariable("id") int id) {
//			return eserv.getEmployeebyId(id);	
//		}
}
