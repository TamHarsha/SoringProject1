Feature: Employee Controller using cucumber

	Scenario: Post new employee
		Given I have employee details to post
		When I call post to add employee
		Then the employee should be added
		
	Scenario: Get employee by ID
		Given To retrive employee with ID 1
		When I send get request to get employee with ID 1
		Then The employee with the ID should be returned
		
	Scenario: Get request to get all employee
		Given Employees are present
		When I send Get request to get all employees
		Then All employee details should br retrived
		
	Scenario: Request for Health check
		When Request sent for health check
		Then service health should return