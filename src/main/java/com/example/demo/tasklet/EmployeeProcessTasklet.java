package com.example.demo.tasklet;

import java.util.*;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.example.demo.data.EmployeeData;

@Component
public class EmployeeProcessTasklet implements Tasklet{


	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		List<EmployeeData> employees = (List<EmployeeData>) chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("EmployeeList");

		//System.out.println(employees);

		chunkContext.getStepContext().getStepExecution().getExecutionContext().put("ProcessedEmployees", employees);

		return RepeatStatus.FINISHED;
	}

}
