package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.CaseDOMapper;
import com.example.demo.dao.ProjectDOMapper;
import com.example.demo.dao.VariableConfigDOMapper;
import com.example.demo.dataobject.CaseDO;
import com.example.demo.dataobject.ProjectDO;
import com.example.demo.dataobject.VariableConfigDO;
import com.example.demo.model.CaseModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

@Slf4j
@Service
public class HttpRequest {

    @Autowired
    ProjectDOMapper projectDOMapper;

    @Autowired
    CaseDOMapper caseDOMapper;

    @Autowired
    VariableConfigDOMapper variableConfigDOMapper;

    public  void doRequest(CaseModule caseModule){
        ProjectDO projectDO = new ProjectDO();
        BeanUtils.copyProperties(caseModule,projectDO);
        ProjectDO projectDO1 = projectDOMapper.selectByModuleNameAndProjectName(projectDO);
        String url = projectDO1.getIpAddress() ;
        String path = caseModule.getPath();
        String requestMethod = caseModule.getRequestMethod();
        String body = caseModule.getBodyValue();
        String headerString = caseModule.getHeaderValue();
        String parameterType = caseModule.getParameterType();

        JSONObject headerJson = JSON.parseObject(headerString);


        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        //获取全局变量
        List<VariableConfigDO> variableConfigDOS = variableConfigDOMapper.selectByCollectionId(0);
        Map globalVariable = new HashMap();
        for (VariableConfigDO variableConfigDO: variableConfigDOS ) {
            globalVariable.put(variableConfigDO.getVariableName(),variableConfigDO.getVariableValue());
            log.info(variableConfigDO.getVariableName()+":"+variableConfigDO.getVariableValue());
        }


//        if(requestMethod.equalsIgnoreCase("get")){
//            HttpGet httpGet = new HttpGet(url+path);
//            for(Map.Entry<String,Object> entry : headerJson.entrySet()){
//                httpGet.addHeader(entry.getKey(), (String) entry.getValue());
//            }
//
//            try {
//                response = httpClient.execute(httpGet);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }else if(requestMethod.equalsIgnoreCase("post")){
//            HttpPost httpPost = new HttpPost(url+path);
//            log.info(url+path);
//            for(Map.Entry<String,Object> entry : headerJson.entrySet()){
//                httpPost.setHeader(entry.getKey(), (String) entry.getValue());
//            }
//            try {
//                if(parameterType.equalsIgnoreCase("json")){
//                httpPost.setEntity(new StringEntity(body, Charset.forName("UTF-8")));
//                }else if(parameterType.equalsIgnoreCase("form")){
//                    List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
//                    JSONObject bodyJson = JSONObject.parseObject(body);
//                    for(Map.Entry entry:bodyJson.entrySet()){
//                      BasicNameValuePair basicNameValuePair =  new BasicNameValuePair(entry.getKey().toString(),entry.getValue().toString());
//                      nameValuePairList.add(basicNameValuePair);
//                    }
//                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList, "UTF-8"));
//                    log.info(String.valueOf(httpPost.getEntity()));
//                }
//                response = httpClient.execute(httpPost);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        HttpEntity responseEntity = response.getEntity();
//        int statusCode = 0 ;
//        String status = String.valueOf(response.getStatusLine());
//        if(status.contains("200")){
//            statusCode = 200;
//        }
//        log.info(String.valueOf(response.getStatusLine()));
//        try {
//            //log.info(EntityUtils.toString(responseEntity,"UTF-8"));
//            String result = EntityUtils.toString(responseEntity,"UTF-8");
//            CaseDO caseDO =new CaseDO();
//            caseDO.setId(caseModule.getId());
//            caseDO.setStatus(statusCode);
//            caseDO.setResult(result);
//            caseDOMapper.updateStatusAndResult(caseDO);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
    }

}
