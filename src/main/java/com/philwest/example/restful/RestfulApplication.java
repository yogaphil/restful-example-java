package com.philwest.example.restful;

import com.philwest.example.restful.controller.ComputerController;
import com.philwest.example.restful.service.ComputerService;
import com.philwest.example.restful.service.ComputerServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * the main application class for this simple web service api example.
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackageClasses = { ComputerController.class })
public class RestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulApplication.class, args);
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("computer-api")
                .select().paths(p -> p.matches("^/computers.*")).build();
    }

    @Bean
    public ComputerService getComputerService() {
        return new ComputerServiceImpl();
    }
}
