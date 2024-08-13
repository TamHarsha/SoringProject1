package com.example.demo.batch;

import java.util.Iterator;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.data.EmployeeData;

@Component
public class EmployeeH2toCSVReader implements ItemReader<EmployeeData> {

	@Autowired
	private EmployeeDao employeeDao;
	
	private Iterator<EmployeeData> iterator;
	
	@Override
	public EmployeeData read() {
			if(iterator == null) {
				iterator = employeeDao.findAll().iterator();
			}
			if(iterator.hasNext()) {
				EmployeeData employee = iterator.next();
				return employee;
			}
			else {
				return null;
			}
	}

}
