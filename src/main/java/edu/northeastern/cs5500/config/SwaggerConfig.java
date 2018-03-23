package edu.northeastern.cs5500.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Praveen Singh
 * Swagger Config File, for loading all the API's in swagger
 */
@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag("course_controller", "Operations pertaining Courses present in the System"),
                        new Tag("file_comparison_controller", "Operations related to file comparison"),
                        new Tag("user_controller", "Operations related to user services"),
                        new Tag("upload_controller", "Operations related to homework upload"),
                        new Tag("homework_controller", "Operations related to homeWorks"),
                        new Tag("student_homework_controller", "Operations related to Student HomeWork Services"));
    }
}
