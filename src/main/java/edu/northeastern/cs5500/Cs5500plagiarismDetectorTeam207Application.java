package edu.northeastern.cs5500;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Cs5500plagiarismDetectorTeam207Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Cs5500plagiarismDetectorTeam207Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Cs5500plagiarismDetectorTeam207Application.class, args);
	}

}