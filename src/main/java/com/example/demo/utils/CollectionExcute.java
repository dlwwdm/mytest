package com.example.demo.utils;

import com.example.demo.dataobject.CaseDO;
import com.example.demo.model.CaseModule;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;

public class CollectionExcute {

    @DataProvider(name = "testData")
    private Iterator<Object[]> getData(){

        return new DataProvider_ForMysql();
    }

    @Test(dataProvider = "testData")
    public void testCase(Integer collectionId, CaseModule caseModule){

        // 返回结果的body


        String response = HttpRequest.doRequest(collectionId,caseModule);



        // 保存变量
        ApiTestUtils.saveVariable(response, caseModule, collectionId);

        // 执行校验
        ApiTestUtils.verifyResult(response, caseModule, collectionId);
    }
}