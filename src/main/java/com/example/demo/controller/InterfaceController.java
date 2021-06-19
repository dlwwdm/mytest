package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dataobject.InterfaceDO;
import com.example.demo.handler.ResponseInfo;
import com.example.demo.service.InterfaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
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

    @RequestMapping(value = "/selectinterfacebyid",method = RequestMethod.GET)
    public ResponseInfo selectInterfaceById(@RequestParam Integer id){
        InterfaceDO interfaceDO = interfaceService.selectByPrimaryKey(id);
        return new ResponseInfo(interfaceDO);
    }

    @RequestMapping(value = "/deleteinterfacebyid",method = RequestMethod.GET)
    public ResponseInfo deleteInterfaceById(@RequestParam Integer id){
        interfaceService.deleteByPrimaryKey(id);
        return new ResponseInfo();
    }

    @RequestMapping(value = "/editinterface",method = RequestMethod.POST,consumes = "application/json")
    public ResponseInfo editInterface(@RequestBody JSONObject jsonObject){
        InterfaceDO interfaceDO = interfaceService.editInterface(jsonObject);
        return new ResponseInfo(interfaceDO);
    }
    @RequestMapping(value = "/selectinterfacebyprojectandmodule",method = RequestMethod.GET)
    public ResponseInfo selectByProjectAndModule(@RequestParam String projectName, String moduleName){
        List<InterfaceDO> interfaceDOList = interfaceService.selectInterfaceByProjectAndModule(projectName, moduleName);
        return new ResponseInfo(interfaceDOList);
    }

    @RequestMapping(value = "/selectbaseinformation",method = RequestMethod.GET)
    public ResponseInfo selectBaseInformation(@RequestParam String projectName, String moduleName, String interfaceName){
        InterfaceDO interfaceDO = interfaceService.selectBaseInformation(projectName, moduleName, interfaceName);
        return new ResponseInfo(interfaceDO);
    }
}
