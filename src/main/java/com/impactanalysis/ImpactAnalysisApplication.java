package com.impactanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.impactanalysis.*")
public class ImpactAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImpactAnalysisApplication.class, args);
	}
}
