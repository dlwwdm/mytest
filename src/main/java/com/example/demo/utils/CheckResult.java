package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dataobject.CaseDO;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CheckResult {

    public void checkResponse(String expectList, String result, CaseDO caseDO){
        JSONArray expectArray = JSONArray.parseArray(expectList);
        for (Object expectString:expectArray) {
            JSONObject expectObject = JSON.parseObject(expectString.toString());
            String getExpectValueMethod = expectObject.getString("getExpectValueMethod");
            String expectValuePath = expectObject.getString("expectValuePath");
            String expectValue = expectObject.getString("expectValue");

            if(getExpectValueMethod.equalsIgnoreCase("jsonPath")){
                Object o = JsonPath.read(result, expectValuePath);
                String actuallyValue = String.valueOf(o);
                if(expectValue.equalsIgnoreCase(actuallyValue)){
                    caseDO.setStatus(1);
                }else {
                    caseDO.setStatus(0);
                }
            }else if(getExpectValueMethod.equalsIgnoreCase("regex")){
                Pattern p = Pattern.compile(expectValuePath);
                Matcher m = p.matcher(result);
                String actuallyValue = "";
                if (m.find()) {
                    actuallyValue = m.group();
                }
                if(expectValue.equalsIgnoreCase(actuallyValue)){
                    caseDO.setStatus(1);
                }else {
                    caseDO.setStatus(0);
                }
            }
        }
    }
}
