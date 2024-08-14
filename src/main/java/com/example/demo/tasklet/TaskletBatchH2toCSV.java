package com.example.demo.tasklet;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TaskletBatchH2toCSV {

	@Autowired
	private EmployeeProcessTasklet employeeProcessor;

	@Autowired
	private EmployeeReaderTasklet employeeReader;

	@Autowired
	private EmployeeWriterTasklet employeeWriter;

	@Bean
	@Qualifier("tasklet")
	public Job H2Tasklet(JobRepository jobRepository,PlatformTransactionManager transaction ) {
		return new JobBuilder("H2toCsvTask",jobRepository)
				.start(step1(jobRepository,transaction))
				.next(step2(jobRepository,transaction))
				.next(step3(jobRepository,transaction))
				.build();
	}

	@Bean
	@Qualifier("tasklet")
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transaction) {
		return new StepBuilder("step1",jobRepository)
				.tasklet(employeeReader,transaction)
				.build();
	}

	@Bean
	@Qualifier("tasklet")
	public Step step2(JobRepository jobRepository, PlatformTransactionManager transaction) {
		return new StepBuilder("step2", jobRepository)
				.tasklet(employeeProcessor,transaction)
				.build();
	}

	@Bean
	@Qualifier("tasklet")
	public Step step3(JobRepository jobRepository, PlatformTransactionManager transaction) {
		return new StepBuilder("step3",jobRepository)
				.tasklet(employeeWriter,transaction)
				.build();
	}

}
