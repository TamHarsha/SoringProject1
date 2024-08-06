package com.example.demo.cucumber;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.bo.EmployeeBo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class EmployeeControllerSteps {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private String url;
	
	//private String url = "http://localhost:8080/api/employee/";
	
	private ResponseEntity<?> response;
	private EmployeeBo empBo;
	
	@Given("I have employee details to post")
	public void post_employee_details() {
		empBo = new EmployeeBo(1, "Harsha", "abc@example.com", "Hyderabad", "1234567890");
	}
	
	@When("I call post to add employee")
	public void request_to_post() {
		//empBo = new EmployeeBo(1, "Harsha", "abc@example.com", "Hyderabad", "1234567890");
		HttpEntity<EmployeeBo> request = new HttpEntity<>(empBo);
		url = "http://localhost:"+port+"/api/employee/";
		response = restTemplate.postForEntity(url+"create", request, String.class);
	}
	
	@Then("the employee should be added")
	public void added_employee_post() {
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("Employee added sucessfully");
	}
	
	@Given("To retrive employee with ID {int}")
	public void get_employee_details_with_ID(int id) {
		empBo = new EmployeeBo(id, "Harsha", "abc@example.com", "Hyderabad", "1234567890");
		HttpEntity<EmployeeBo> request = new HttpEntity<>(empBo);
		url = "http://localhost:"+port+"/api/employee/";
		response = restTemplate.postForEntity(url+"create", request, String.class);
	}
	
	@When("I send get request to get employee with ID {int}")
	public void request_to_get_employee_withID(int id) {
		url = "http://localhost:"+port+"/api/employee/";
		response = restTemplate.getForEntity(url+"retrieval/"+id, EmployeeBo.class);
	}
	
	@Then("The employee with the ID should be returned")
	public void retrive_employee_eith_ID() {
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		EmployeeBo emp = (EmployeeBo)response.getBody();
		assertThat(emp.getEmployeeName()).isEqualTo("Harsha");
	}
	
	@Given("Employees are present")
    public void employees_exist() {
        EmployeeBo empBo1 = new EmployeeBo(1, "Harsha", "abc@example.com", "Hyderabad", "1234567890");
        EmployeeBo empBo2 = new EmployeeBo(2, "Sai", "sai@example.com", "Hyderabad", "1234567890");
        url = "http://localhost:"+port+"/api/employee";
        restTemplate.postForEntity(url + "/create", new HttpEntity<>(empBo1), String.class);
        restTemplate.postForEntity(url + "/create", new HttpEntity<>(empBo2), String.class);
    }

    @When("I send Get request to get all employees")
    public void i_send_get_request_to_get_all_employees() {
    	url = "http://localhost:"+port+"/api/employee";
        response = restTemplate.exchange(url + "/all", HttpMethod.GET, null, List.class);
    }

    @Then("All employee details should br retrived")
    public void i_should_get_a_list_of_employees() {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<EmployeeBo> employees = (List<EmployeeBo>) response.getBody();
        assertThat(employees).hasSize(2);
    }
    
    @When("Request sent for health check")
    public void get_health_check() {
    	url = "http://localhost:"+port+"/api/employee/";
    	response = restTemplate.getForEntity(url+"health", String.class);
    }
    
    @Then("service health should return")
    public void retriving_gealth_check() {
    	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    	assertThat(response.getBody()).isEqualTo("Service is up and running!");
    }

}
