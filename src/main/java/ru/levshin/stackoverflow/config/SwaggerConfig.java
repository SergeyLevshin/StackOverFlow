package ru.levshin.stackoverflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis (RequestHandlerSelectors.basePackage("ru.levshin.stackoverflow.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title ("Bars Hunters Office RESTful API")
                .contact(new Contact("ЦИТ БАРС", "http://barsim.ru/content/kontakty", "arenda@bars-arenda.ru"))
                .description ("Барс Кабинет Охотника описание API")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
