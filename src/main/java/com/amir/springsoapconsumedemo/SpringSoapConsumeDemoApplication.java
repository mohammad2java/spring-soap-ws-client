package com.amir.springsoapconsumedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSoapConsumeDemoApplication implements CommandLineRunner {
	
	@Autowired
	private CalculatorServiceClient calculatorServiceClient;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSoapConsumeDemoApplication.class, args).close();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("staretd....");
		int addTwoNumber2 = calculatorServiceClient.addTwoNumber(20, 30);
		System.out.println(addTwoNumber2);
		String currency = calculatorServiceClient.getCountry("India");
		System.out.println(currency);
		System.out.println("end....");
	}

}
