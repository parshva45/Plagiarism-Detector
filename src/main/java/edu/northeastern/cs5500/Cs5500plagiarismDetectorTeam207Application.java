package edu.northeastern.cs5500;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class Cs5500plagiarismDetectorTeam207Application extends SpringBootServletInitializer {
	private static final Logger LOGGER =
			LogManager.getLogger(Cs5500plagiarismDetectorTeam207Application.class);


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Cs5500plagiarismDetectorTeam207Application.class);
	}

	public static void main(String[] args) {
		LOGGER.info("Starting application");
		SpringApplication.run(Cs5500plagiarismDetectorTeam207Application.class, args);
	}

}