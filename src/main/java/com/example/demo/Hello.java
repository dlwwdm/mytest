package com.example.demo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Hello {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello springBoot~~";
    }
}