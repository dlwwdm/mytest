package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication(scanBasePackages = {"com.example.demo"})
@MapperScan("com.example.demo.dao")
@Configuration
public class DemoApplication  {

    public static boolean started = false;
    private static ApplicationContext applicationContext;

    public static ApplicationContext get(){
        return applicationContext;
    }

    public static void main(String[] args) {

        //SpringApplication.run(DemoApplication.class, args);
        applicationContext = SpringApplication.run(DemoApplication.class, args);
        started = true;
    }

}
