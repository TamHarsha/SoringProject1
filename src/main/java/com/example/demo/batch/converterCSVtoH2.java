package com.example.demo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.demo.data.EmployeeData;

@Configuration
public class converterCSVtoH2 {
	
	@Bean
	@Qualifier("csvtoh2Job")
	public Job csvToH2(JobRepository jobRepository,@Qualifier("csvtoh2Step") Step csvToH2Step) {
		
		return new JobBuilder("CsvtoH2Job",jobRepository)
				.start(csvToH2Step)
				.build();
	}
	
	@Bean
	@Qualifier("csvtoh2Step")
	public Step csvToH2Step(JobRepository jobRepository, PlatformTransactionManager transactionManager,
			EmployeeCSVtoH2Writer writer, EmployeeCSVtoH2Reader reader, EmployeeProcessor processor) {
				
		return new StepBuilder("CsvToH2Step", jobRepository)
                .<EmployeeData, EmployeeData>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
		
	}

}
