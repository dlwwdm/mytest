package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dataobject.CaseDO;
import com.example.demo.handler.ResponseInfo;
import com.example.demo.model.CaseModule;
import com.example.demo.service.CaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CaseController {

    @Autowired
    CaseService caseService;

    @RequestMapping(value = "/listcase",method = RequestMethod.GET)
    public ResponseInfo listCase(){
        List<CaseModule> caseModuleList = caseService.listCase();
        return new ResponseInfo(caseModuleList);
    }

    @RequestMapping(value = "/addcase",method = RequestMethod.POST,consumes = "application/json")
    public ResponseInfo addCase(@RequestBody JSONObject jsonObject){
        CaseModule caseModule = caseService.addCase(jsonObject);
        return new ResponseInfo(caseModule);
    }

}
