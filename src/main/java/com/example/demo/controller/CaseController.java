package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dataobject.CaseDO;
import com.example.demo.handler.ResponseInfo;
import com.example.demo.model.CaseModule;
import com.example.demo.service.CaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
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
        log.info(jsonObject.toString());
        CaseModule caseModule = caseService.addCase(jsonObject);
        return new ResponseInfo(caseModule);
    }

    @RequestMapping(value = "/deletecase",method = RequestMethod.GET)
    public ResponseInfo deleteCase(@RequestParam Integer id){
        caseService.deleteCase(id);
        return new ResponseInfo();
    }

}
