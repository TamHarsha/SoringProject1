package com.example.demo.tasklet;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.data.EmployeeData;



@Component
public class EmployeeReaderTasklet implements Tasklet {

	@Autowired
	private  EmployeeDao employeeDao;



	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		List<EmployeeData> employee = employeeDao.findAll(); 

		chunkContext.getStepContext().getStepExecution().getJobExecution() .getExecutionContext().put("EmployeeList", employee);
		//System.out.println(employee);
		return RepeatStatus.FINISHED;
	}

}
