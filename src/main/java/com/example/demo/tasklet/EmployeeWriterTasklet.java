package com.example.demo.tasklet;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.example.demo.data.EmployeeData;

@Component
public class EmployeeWriterTasklet implements Tasklet {

	private static final String filename = "src/main/resources/h2Tasklet.csv";
	private static final String fileHeader = "EmpId,Name,EmailId,Address,Number";

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		List<EmployeeData> employees = (List<EmployeeData>) chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("EmployeeList");

		//System.out.println();

		//        // Create a new file if it does not exist
		//        File file = new File(filename);
		//        if (!file.exists()) {
		//      file.createNewFile();  // Create a new empty file
		//        }

		try (FileWriter writer = new FileWriter(filename)) {

			if (new java.io.File(filename).length() == 0) {
				writer.write(fileHeader + "\n");
			}
			// Write employee data
			for (EmployeeData employee : employees) {
				writer.write(String.format("%d,%s,%s,%s,%s\n",
						employee.getEmpId(),
						employee.getEmployeeName(),
						employee.getEmployeeEmailId(),
						employee.getEmployeeAddress(),
						employee.getEmployeeNumber()));
			}
		} catch (IOException e) {
			e.printStackTrace();
			// Handle the exception according to your application's needs
		}

		return RepeatStatus.FINISHED;
	}

}
