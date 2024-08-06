Feature: Employee Management

  Scenario: Create a new employee
    Given I have employee details
    When I create the employee
    Then the employee is created successfully

  Scenario: Retrieve an employee by ID
    Given an employee exists with ID 1
    When I retrieve the employee by ID 1
    Then the employee details are returned
    
  Scenario: Retrieve all employee
  	Given there are multiple employee
  	When I retrive all employee
  	Then All Employee details are returned
