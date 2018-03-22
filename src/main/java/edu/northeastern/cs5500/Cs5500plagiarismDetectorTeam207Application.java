package edu.northeastern.cs5500;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class Cs5500plagiarismDetectorTeam207Application extends SpringBootServletInitializer
		implements ApplicationRunner {
	private static final Logger LOGGER =
			LogManager.getLogger(Cs5500plagiarismDetectorTeam207Application.class);


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Cs5500plagiarismDetectorTeam207Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Cs5500plagiarismDetectorTeam207Application.class, args);
	}

	@Override
	public void run(ApplicationArguments applicationArguments) {
		LOGGER.debug("Debugging log");
		LOGGER.info("Info log");
		LOGGER.warn("Hey, This is a warning!");
		LOGGER.error("Oops! We have an Error. OK");
		LOGGER.fatal("Damn! Fatal error. Please fix me.");
	}

}