package com.example.demo.cucumber;

import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.example.demo.bo.EmployeeBo;
import com.example.demo.service.EmployeeService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeServiceSteps {
	
	 	@Autowired
	    private EmployeeService employeeService;

	    private EmployeeBo employeeBo;
	    private EmployeeBo createdEmployee;
	    List<EmployeeBo> allEmp;

	    @Given("I have employee details")
	    public void i_have_employee_details() {
	        employeeBo = new EmployeeBo(1, "Harsha", "abc@example.com", "Hyderabad", "1234567890");
	    }

	    @When("I create the employee")
	    public void i_create_the_employee() {
	        createdEmployee = employeeService.createEmployee(employeeBo);
	    }

	    @Then("the employee is created successfully")
	    public void the_employee_is_created_successfully() {
	        assertEquals(employeeBo.getEmployeeName(), createdEmployee.getEmployeeName());
	    }

	    @Given("an employee exists with ID {int}")
	    public void an_employee_exists_with_id(Integer id) {
	        employeeBo = new EmployeeBo(id, "Harsha", "abc@example.com", "Hyderabad", "1234567890");
	        employeeService.createEmployee(employeeBo);
	    }

	    @When("I retrieve the employee by ID {int}")
	    public void i_retrieve_the_employee_by_id(Integer id) {
	        createdEmployee = employeeService.getEmployeebyId(id);
	    }

	    @Then("the employee details are returned")
	    public void the_employee_details_are_returned() {
	        assertEquals(employeeBo.getEmpId(), createdEmployee.getEmpId());
	    }
	    
	    @Given("there are multiple employee")
	    public void multiple_employee() {
	    	 EmployeeBo employeeBo1 = new EmployeeBo(1, "Harsha", "abc@example.com", "Hyderabad", "1234567890");
	    	 EmployeeBo employeeBo2 = new EmployeeBo(2, "Sai", "sai@example.com", "Hyderabad", "1234567890");
	    	 employeeService.createEmployee(employeeBo1);
	    	 employeeService.createEmployee(employeeBo2);
	    }
	    
	    @When("I retrive all employee")
	    public void get_all_employee() {
	    	allEmp = employeeService.getAllEmployee();
	    }
	    
	    @Then("All Employee details are returned")
	    public void return_all_employee() {
	    	
	    	assertEquals(2, allEmp.size());
	    }


}
