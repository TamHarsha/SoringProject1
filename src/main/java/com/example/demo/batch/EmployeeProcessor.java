package com.example.demo.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.demo.data.EmployeeData;

@Component
public class EmployeeProcessor implements ItemProcessor<EmployeeData, EmployeeData> {

	@Override
	public EmployeeData process(EmployeeData employee){
		
		return employee;
	}

}
