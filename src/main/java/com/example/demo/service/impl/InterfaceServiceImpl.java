package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.InterfaceDOMapper;
import com.example.demo.dataobject.InterfaceDO;
import com.example.demo.handler.BusinessErrorException;
import com.example.demo.handler.BusinessMsgEnum;
import com.example.demo.service.InterfaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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
    public InterfaceDO selectByPrimaryKey(Integer id) {
        return interfaceDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteByPrimaryKey(Integer id) {
        log.info("要删除的接口ID为："+id);
        interfaceDOMapper.deleteByPrimaryKey(id);
        log.info("删除成功");
    }

    @Override
    public List<InterfaceDO> listInterface() {
        List<InterfaceDO> interfaceDOList = interfaceDOMapper.listInterface();
        return interfaceDOList;
    }

    @Override
    public InterfaceDO editInterface(JSONObject jsonObject) {
        InterfaceDO interfaceDO = JSONObject.toJavaObject(jsonObject, InterfaceDO.class);
        interfaceDOMapper.updateByPrimaryKeySelective(interfaceDO);
        return interfaceDOMapper.selectByPrimaryKey(interfaceDO.getId());
    }

    @Override
    public List<InterfaceDO> selectInterfaceByProjectAndModule(String projectName, String moduleName) {
        return interfaceDOMapper.selectInterfaceByProjectAndModule(projectName, moduleName);
    }

    @Override
    public InterfaceDO selectBaseInformation(String projectName, String moduleName, String interfaceName) {
        return interfaceDOMapper.selectBaseInformation(projectName, moduleName, interfaceName);
    }
}
