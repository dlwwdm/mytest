package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.InterfaceDOMapper;
import com.example.demo.dataobject.InterfaceDO;
import com.example.demo.handler.BusinessErrorException;
import com.example.demo.handler.BusinessMsgEnum;
import com.example.demo.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterfaceServiceImpl implements InterfaceService {

    @Autowired
    InterfaceDOMapper interfaceDOMapper;

    @Override
    public InterfaceDO addInterface(JSONObject jsonObject) {
        if(jsonObject instanceof JSONObject ){

            InterfaceDO interfaceDO = JSONObject.toJavaObject(jsonObject, InterfaceDO.class);
            interfaceDOMapper.insertSelective(interfaceDO);
            return interfaceDOMapper.selectByPrimaryKey(interfaceDO.getId());
        }
        throw new BusinessErrorException(BusinessMsgEnum.PARAMETER_VALIDATION_EXCEPTION);
    }

    @Override
    public List<InterfaceDO> listInterface() {
        List<InterfaceDO> interfaceDOList = interfaceDOMapper.listInterface();
        return interfaceDOList;
    }
}
