package com.example.demo.service.impl;

import com.example.demo.dao.CaseDOMapper;
import com.example.demo.dao.InterfaceDOMapper;
import com.example.demo.dao.ProjectDOMapper;
import com.example.demo.dataobject.CaseDO;
import com.example.demo.dataobject.InterfaceDO;
import com.example.demo.dataobject.ProjectDO;
import com.example.demo.model.CaseModule;
import com.example.demo.service.ApiTestService;
import com.example.demo.utils.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ApiTestServiceImp implements ApiTestService {

    @Autowired
    CaseDOMapper caseDOMapper;

    @Autowired
    InterfaceDOMapper interfaceDOMapper;

    @Autowired
    ProjectDOMapper projectDOMapper;

    @Autowired
    HttpRequest httpRequest;

    @Override
    public void executeCase(Integer collectionId,List<Integer> apiList) {
        convertCaseModuleFromCaseDO(collectionId,apiList);
    }

    public void convertCaseModuleFromCaseDO(Integer collectionId,List<Integer> apiList){
        List<CaseDO> caseDOList = caseDOMapper.selectByIdList(apiList);
        for (CaseDO caseDO:caseDOList) {
            log.info(caseDO.toString());
            CaseModule caseModule = new CaseModule();
            Integer interfaceId = caseDO.getInterfaceId();
            InterfaceDO interfaceDO = interfaceDOMapper.selectByPrimaryKey(interfaceId);
            BeanUtils.copyProperties(interfaceDO,caseModule);
            BeanUtils.copyProperties(caseDO,caseModule);
            httpRequest.doRequest(collectionId,caseModule);
        }
    }
}
