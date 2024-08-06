package com.example.demo.cucumber;

import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.SpringProject1Application;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = SpringProject1Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class CucumberConfiguration {

}
