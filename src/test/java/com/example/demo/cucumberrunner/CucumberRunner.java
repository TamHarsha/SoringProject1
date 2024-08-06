package com.example.demo.cucumberrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/java/com/example/demo/features"},
		glue = {"com.example.demo.cucumber"},
	    plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"}
		)
public class CucumberRunner {

}
