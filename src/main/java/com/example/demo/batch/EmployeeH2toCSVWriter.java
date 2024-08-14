package com.example.demo.batch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.example.demo.data.EmployeeData;

@Component
public class EmployeeH2toCSVWriter implements ItemWriter<EmployeeData> {
	private static final String fileheader = "ID,Name,EmailId,Address,PhoneNumber";
	private static final String filename = "src/main/resources/h2tocsv.csv";

	@Override
	public void write(Chunk<? extends EmployeeData> chunk) throws Exception {
		// TODO Auto-generated method stub
//		java.io.File file = new java.io.File(filename);
//		if (!file.exists()) {
//		    file.createNewFile();  // This will create a new empty file
//		}
		
		try(FileWriter writer = new FileWriter(filename)){
			
			 if (new java.io.File(filename).length() == 0) {
	                writer.write(fileheader + "\n");
	            }
	            for (EmployeeData employee : chunk) {
	                writer.write(String.format("%d,%s,%s,%s,%s\n", employee.getEmpId(), 
	                		employee.getEmployeeName(), employee.getEmployeeEmailId(), employee.getEmployeeAddress(),employee.getEmployeeNumber()));
	                
	            }
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
