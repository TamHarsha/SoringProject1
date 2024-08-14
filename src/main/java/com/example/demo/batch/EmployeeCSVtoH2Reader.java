package com.example.demo.batch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import com.example.demo.data.EmployeeData;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmployeeCSVtoH2Reader implements ItemReader<EmployeeData>{

	private static final String filename = "src/main/resources/employee.csv";

	private BufferedReader reader;
	private String currentLine;

	private boolean initialized = false;


	@Override
	public EmployeeData read() {

		try {

			if(!initialized) {
				reader = new BufferedReader(new FileReader(filename));
				reader.readLine();
				initialized = true;
			}

			if ((currentLine = reader.readLine()) != null) {
				String[] employeeData = currentLine.split("\t");

				// Handle the case where CSV data might have leading/trailing spaces
				for (int i = 0; i < employeeData.length; i++) {
					employeeData[i] = employeeData[i].trim();
				}

				EmployeeData employee = new EmployeeData();
				//  employee.setEmpId(Integer.parseInt(employeeData[0]));
				employee.setEmployeeName(employeeData[1]);
				employee.setEmployeeEmailId(employeeData[2]);
				employee.setEmployeeAddress(employeeData[3]);
				employee.setEmployeeNumber(employeeData[4]);
				// log.info("Reading employee: {}", employee);
				return employee;

			}
			else {
				// Close the reader when done
				reader.close();
				initialized = false; // Reset initialization status for the next run
			}
		} catch (IOException e) {
			log.error("Error reading the CSV file", e);
		} catch (NumberFormatException e) {
			log.error("Error parsing number from CSV data", e);
		}
		return null;
	}

}
