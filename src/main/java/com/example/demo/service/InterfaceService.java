package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dataobject.InterfaceDO;

import java.util.List;

public interface InterfaceService {
    InterfaceDO addInterface(JSONObject jsonObject);

    List<InterfaceDO> listInterface();
}
