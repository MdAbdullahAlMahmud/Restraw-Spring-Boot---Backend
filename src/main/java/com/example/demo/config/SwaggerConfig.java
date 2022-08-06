package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    Contact contact = new Contact(
            "Code Smasher",
            "www.wegrapps.com",
            "abdullah15-12316@diu.edu.bd"
    );
    List<VendorExtension> vendorExtensions = new ArrayList<>();
    ApiInfo apiInfo = new ApiInfo(
            "Restraw App Web Service Documentation",
            "This pages display Restraw endpoints",
            "1.0",
            "www.google.com",
            contact,
            "Apache 2.0",
            "",
            vendorExtensions
    );
    @Bean
    public Docket apiDocket(){

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .paths(PathSelectors.any())
                .build();
        return  docket;
    }

}
