package com.example.demo.controller;

import com.example.demo.service.ApiTestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.expr.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ApiTestController {
    @Autowired
    ApiTestService apiTestService;

    @RequestMapping(value = "/executecase" ,method = RequestMethod.POST ,consumes = "application/json")
    public void executeCase(@RequestBody List<Integer> apiList){
        for (Integer id:apiList
             ) {
            log.info(id.toString());
        }
        apiTestService.executeCase(apiList);
    }
}
