package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.VariableConfigDOMapper;
import com.example.demo.dataobject.VariableConfigDO;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class SaveVariable {

    @Autowired
    VariableConfigDOMapper variableConfigDOMapper;

    public void saveCollectionVariable(Integer collectionId,String variableList,String result){
        JSONArray jsonArray = JSONArray.parseArray(variableList);
        for (Object variable:jsonArray) {
            VariableConfigDO variableConfigDO = new VariableConfigDO();
            variableConfigDO.setCollectionId(collectionId);
            JSONObject variableObject = JSON.parseObject(variable.toString());
            String getVariableMethod = variableObject.getString("getVariableMethod");
            if(getVariableMethod.equalsIgnoreCase("jsonPath")){
                Object o = JsonPath.read(result, variableObject.getString("variableValue"));
                String variableValue = String.valueOf(o);
                variableConfigDO.setVariableName(variableObject.getString("variableName"));
                variableConfigDO.setVariableValue(variableValue);
                Integer id = variableConfigDOMapper.selectIdByCollectionIdAndVariableName(variableConfigDO);
                variableConfigDO.setId(id);
                variableConfigDOMapper.insertAndUpdate(variableConfigDO);
            }else if(getVariableMethod.equalsIgnoreCase("regex")){
                String extractRule = variableObject.getString("variableValue");
                Pattern p = Pattern.compile(extractRule);
                Matcher m = p.matcher(result);
                String variableValue = "";
                if (m.find()) {
                    variableValue = m.group();
                }
                variableConfigDO.setVariableName(variableObject.getString("variableName"));
                variableConfigDO.setVariableValue(variableValue);
                Integer id = variableConfigDOMapper.selectIdByCollectionIdAndVariableName(variableConfigDO);
                log.info(String.valueOf(id));
                variableConfigDO.setId(id);
                variableConfigDOMapper.insertAndUpdate(variableConfigDO);
            }
        }
    }
}
