package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.VariableConfigDOMapper;
import com.example.demo.dataobject.VariableConfigDO;
import com.example.demo.service.VariableConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VariableConfigImp implements VariableConfigService {

    @Autowired
    VariableConfigDOMapper variableConfigDOMapper;

    @Override
    public void addGlobalVariable(JSONObject jsonObject) {
        VariableConfigDO variableConfigDO = JSONObject.toJavaObject(jsonObject,VariableConfigDO.class);
        variableConfigDOMapper.insertSelective(variableConfigDO);
    }
}
