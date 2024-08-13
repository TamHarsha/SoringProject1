package com.example.demo.batch;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.data.EmployeeData;

@Component
public class EmployeeCSVtoH2Writer implements ItemWriter<EmployeeData> {

	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public void write(Chunk<? extends EmployeeData> chunk) throws Exception {
		// TODO Auto-generated method stub
		employeeDao.saveAll(chunk.getItems());
	}
	
	
}
