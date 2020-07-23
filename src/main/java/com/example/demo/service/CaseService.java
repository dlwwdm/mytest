package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dataobject.CaseDO;
import com.example.demo.model.CaseModule;

import java.util.List;

public interface CaseService {
    List<CaseModule> listCase();

    CaseModule addCase(JSONObject jsonObject);
}
