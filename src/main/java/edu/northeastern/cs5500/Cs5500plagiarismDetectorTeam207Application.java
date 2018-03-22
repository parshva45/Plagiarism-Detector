package edu.northeastern.cs5500;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class Cs5500plagiarismDetectorTeam207Application extends SpringBootServletInitializer {
	private static final Logger LOGGER =
			LogManager.getLogger(Cs5500plagiarismDetectorTeam207Application.class);


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		LOGGER.info("Starting application");
		return application.sources(Cs5500plagiarismDetectorTeam207Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Cs5500plagiarismDetectorTeam207Application.class, args);
	}

}