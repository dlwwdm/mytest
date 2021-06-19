package com.example.demo.utils;

import com.example.demo.DemoApplication;
import com.example.demo.dataobject.CaseDO;
import com.example.demo.model.CaseModule;
import com.example.demo.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

@Slf4j
@Service

@SpringBootTest(classes = DemoApplication.class)
public class CollectionExecute   {

    public static ApplicationContext applicationContext;

    @Autowired
    HttpRequest httpRequest;


    @BeforeClass
    public void start(){
        if(!DemoApplication.started){
            applicationContext = SpringApplication.run(DemoApplication.class);
            DemoApplication.started = true;

        }else{
            applicationContext = DemoApplication.get();
        }
    }


    @DataProvider(name = "testData")
    private Iterator<Object[]> getData(){
        return DataProvider_ForMysql.getData(ApiConfig.list);
    }

    @Test(dataProvider = "testData")
    public void testCase(CaseModule caseModule){

        // 返回结果的body
        log.info("进入用例主体");
        //log.info(httpRequest.toString());
        httpRequest = applicationContext.getBean(HttpRequest.class);
        log.info(httpRequest.toString());
        String response = httpRequest.doRequest(caseModule.getCollectionId(),caseModule);
        log.info(response);



        // 保存变量
        SaveVariable saveVariable = new SaveVariable();
        saveVariable.saveCollectionVariable(caseModule.getCollectionId(),caseModule.getVariableList(),response);

        // 执行校验
        CaseDO caseDO = new CaseDO();
        caseDO.setId(caseModule.getId());
        caseDO.setResult(response);
        CheckResult checkResult = new CheckResult();
        checkResult.checkResponse(caseModule.getExpectList(),response,caseDO);
    }
}
