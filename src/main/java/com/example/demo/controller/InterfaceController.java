package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dataobject.InterfaceDO;
import com.example.demo.handler.ResponseInfo;
import com.example.demo.service.InterfaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class InterfaceController {

    @Autowired
    InterfaceService interfaceService;

    @RequestMapping(value = "/addinterface",method = RequestMethod.POST,consumes = "application/json;charset=UTF-8")
    public ResponseInfo addInterface(@RequestBody JSONObject jsonObject){
        log.info(String.valueOf(jsonObject));
        InterfaceDO interfaceDO = interfaceService.addInterface(jsonObject);
        return new ResponseInfo(interfaceDO);
    }

    @RequestMapping(value = "/listinterface",method = RequestMethod.GET)
    public ResponseInfo listInterface(){
        List<InterfaceDO> interfaceDOList = interfaceService.listInterface();
        return new ResponseInfo(interfaceDOList);
    }
}
