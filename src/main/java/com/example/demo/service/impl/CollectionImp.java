package com.example.demo.service.impl;

import com.example.demo.dao.CollectionDOMapper;
import com.example.demo.dataobject.CollectionDO;
import com.example.demo.service.ApiTestService;
import com.example.demo.service.CollectionService;
import com.example.demo.utils.ExtentTestNGIReporterListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CollectionImp implements CollectionService {

    @Autowired
    CollectionDOMapper collectionDOMapper;

    @Autowired
    ApiTestService apiTestService;

    @Override
    public void executeCollection(List<Integer> collectionId) {
        CollectionDO collectionDO = collectionDOMapper.selectByPrimaryKey(collectionId.get(0));
        String caseString = collectionDO.getCaseList();
        String str = caseString.substring(1,caseString.length()-1);
        List<String> list = Arrays.asList(str.split(","));
        List<Integer> caseList = new ArrayList<>();
        for (String s:list) {
            caseList.add(Integer.valueOf(s));
        }
        apiTestService.executeCase(collectionDO.getId(),caseList);
    }

    public void execute(){
        TestNG testNg = new TestNG();
        Class[] listenerClass = {ExtentTestNGIReporterListener.class};
        testNg.setListenerClasses(Arrays.asList(listenerClass));

        testNg.setTestClasses(new Class[]{CollectionExcute.class});
        testNg.run();
    }
}
