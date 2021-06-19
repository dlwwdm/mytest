package com.example.demo.utils;


import com.example.demo.dataobject.CaseDO;
import com.example.demo.dataobject.InterfaceDO;
import com.example.demo.model.CaseModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class DataProvider_ForMysql  {

    @Autowired


    @DataProvider
        public static Iterator<Object[]> getData(List<CaseModule> caseModules){
        List<Object[]> caseModules1 = new ArrayList<>();

        for (CaseModule caseModule : caseModules) {
            //做一个形式转换
            log.info(caseModule.toString());
            caseModules1.add(new CaseModule[] { caseModule });
        }

        return caseModules1.iterator();
    }
    }

