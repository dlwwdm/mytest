package com.example.demo.service.impl;

import com.example.demo.dao.CollectionDOMapper;
import com.example.demo.dataobject.CollectionDO;
import com.example.demo.service.ApiTestService;
import com.example.demo.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
