package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dataobject.InterfaceDO;

import java.util.List;

public interface InterfaceService {
    InterfaceDO addInterface(JSONObject jsonObject);

    InterfaceDO selectByPrimaryKey(Integer id);

    void deleteByPrimaryKey(Integer id);

    List<InterfaceDO> listInterface();


    InterfaceDO editInterface(JSONObject jsonObject);

    List<InterfaceDO> selectInterfaceByProjectAndModule(String projectName, String moduleName);

    InterfaceDO selectBaseInformation(String projectName, String moduleName, String interfaceName);
}
