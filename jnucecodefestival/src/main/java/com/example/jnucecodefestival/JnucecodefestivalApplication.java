package com.example.jnucecodefestival;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@SpringBootApplication
public class JnucecodefestivalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JnucecodefestivalApplication.class, args);
    }
}
