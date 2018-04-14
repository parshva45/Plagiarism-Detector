//package edu.northeastern.cs5500.config;
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
//* @author Praveen Singh
//* Swagger Config File, for loading all the API's in swagger
//*/
//@Configuration
//@EnableSwagger2
//@EnableAutoConfiguration
//public class SwaggerConfig {
//   @Bean
//   public Docket api() {
//       return new Docket(DocumentationType.SWAGGER_2)
//               .select()
//               .apis(RequestHandlerSelectors.any())
//               .paths(PathSelectors.any())
//               .build();
//   }
//}