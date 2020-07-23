package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.VariableConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VariableConfigController {

    @Autowired
    VariableConfigService variableConfigService;

    @RequestMapping(value = "addglobalvariable",method = RequestMethod.POST,consumes = "application/json")
    public void addGlobalVariable(@RequestBody JSONObject jsonObject){
        variableConfigService.addGlobalVariable(jsonObject);
    }
}
