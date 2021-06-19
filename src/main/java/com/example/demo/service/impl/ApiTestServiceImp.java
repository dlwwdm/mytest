package com.example.demo.service.impl;

import com.example.demo.dao.CaseDOMapper;
import com.example.demo.dao.InterfaceDOMapper;
import com.example.demo.dao.ProjectDOMapper;
import com.example.demo.dataobject.CaseDO;
import com.example.demo.dataobject.InterfaceDO;
import com.example.demo.model.CaseModule;
import com.example.demo.service.ApiTestService;
import com.example.demo.utils.ApiConfig;
import com.example.demo.utils.CollectionExecute;
import com.example.demo.utils.ExtentTestNGIReporterListener;
import com.example.demo.utils.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.TestNG;

import java.util.Arrays;
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
        ApiConfig.list.clear();
        for (CaseDO caseDO:caseDOList) {
            log.info(caseDO.toString());
            CaseModule caseModule = new CaseModule();
            Integer interfaceId = caseDO.getInterfaceId();
            InterfaceDO interfaceDO = interfaceDOMapper.selectByPrimaryKey(interfaceId);
            BeanUtils.copyProperties(interfaceDO,caseModule);
            BeanUtils.copyProperties(caseDO,caseModule);
            caseModule.setCollectionId(collectionId);
            log.info(caseModule.toString());
            ApiConfig.list.add(caseModule);
            log.info(String.valueOf(ApiConfig.list.size()));
        }
        this.execute();

    }
    public void execute(){
        TestNG testNg = new TestNG();
        log.info("进入到testng中");
        Class[] listenerClass = {ExtentTestNGIReporterListener.class};
        testNg.setListenerClasses(Arrays.asList(listenerClass));

        testNg.setTestClasses(new Class[]{CollectionExecute.class});
        testNg.run();
    }
}
