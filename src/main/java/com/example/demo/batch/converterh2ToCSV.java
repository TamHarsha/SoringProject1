package com.example.demo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.demo.data.EmployeeData;

@Configuration
public class converterh2ToCSV {
	
	@Bean
	@Qualifier("h2tocsvJob")
	Job employeeFromH2toCSV(JobRepository jobRepository, @Qualifier("h2tocsvStep")Step employeeFromH2toCSVStrp) {
		
		return new  JobBuilder("H2ToCSVJob",jobRepository).incrementer(new RunIdIncrementer())
				.start(employeeFromH2toCSVStrp)
				.build();
	}
	
	@Bean
	@Qualifier("h2tocsvStep")
	Step employeeFromH2toCSVStrp(JobRepository jobRepository, PlatformTransactionManager transactionManager ,EmployeeH2toCSVReader reader, EmployeeProcessor processor, EmployeeH2toCSVWriter writer) {
		
		return new StepBuilder("H2ToCsvStep", jobRepository).<EmployeeData, EmployeeData>chunk(5,transactionManager)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}

}
