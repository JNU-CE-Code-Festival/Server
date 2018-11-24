package com.example.jnucecodefestival;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("hello");
        // registry.addViewController("/babo").setViewName("code");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/code").setViewName("code");
    }

    // @Bean(name = "dataSource")
    // public DriverManagerDataSource dataSource() {
    //     DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    //     driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    //     driverManagerDataSource.setUrl("jdbc:mysql://172.18.102.128/programming_contest?characterEncoding=utf8");
    //     driverManagerDataSource.setUsername("contest");
    //     driverManagerDataSource.setPassword("sslab08295860");

    //     return driverManagerDataSource;
    // }

    /*@Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");

        return resolver;
    }*/
}
