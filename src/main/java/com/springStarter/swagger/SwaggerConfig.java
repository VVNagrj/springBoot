package com.springStarter.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfigurations() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springStarter"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails()).forCodeGeneration(true);
    }

    private ApiInfo apiDetails() {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        Collection<VendorExtension> list = Collections.EMPTY_LIST;
        return new ApiInfo(
                "Basic Spring Application",
                "Basic Spring Application",
                "1.0",
                "",
                new springfox.documentation.service.Contact("Nagarajan", "https://www.linkedin.com/in/nagarajan-vv-910323108/", "nagrjvv@gmail.com"),
                "API License",
                "TBD-License URL",list);

    }
}
