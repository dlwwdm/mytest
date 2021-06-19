package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.CaseDOMapper;
import com.example.demo.dao.InterfaceDOMapper;
import com.example.demo.dataobject.CaseDO;
import com.example.demo.dataobject.InterfaceDO;
import com.example.demo.dataobject.UserPasswordDO;
import com.example.demo.model.CaseModule;
import com.example.demo.model.UserModel;
import com.example.demo.service.CaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Case;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CaseServiceImpl implements CaseService {
    @Autowired
    CaseDOMapper caseDOMapper;

    @Autowired
    InterfaceDOMapper interfaceDOMapper;

    @Override
    public List<CaseModule> listCase() {
        List<CaseDO> caseDOList = caseDOMapper.listCase();
        List<CaseModule> caseModuleList = caseDOList.stream().map(caseDO -> {
            InterfaceDO interfaceDO = interfaceDOMapper.selectByPrimaryKey(caseDO.getInterfaceId());
            CaseModule caseModule = this.coverCaseModuleFromCaseAndInterface(caseDO,interfaceDO);
            return caseModule;
        }).collect(Collectors.toList());
        return caseModuleList;
    }

    @Override
    public CaseModule addCase(JSONObject jsonObject) {
        CaseModule caseModule = JSONObject.toJavaObject(jsonObject,CaseModule.class);
        log.info(caseModule.toString());
        CaseDO caseDO = this.coverCaseDOFromCaseModule(caseModule);
        log.info(caseDO.toString());
        caseDOMapper.insertSelective(caseDO);
        return this.coverCaseModuleFromCase(caseDOMapper.selectByPrimaryKey(caseDO.getId()));
    }

    @Override
    public void deleteCase(Integer id) {
        caseDOMapper.deleteByPrimaryKey(id);
    }

    public CaseModule coverCaseModuleFromCaseAndInterface(CaseDO caseDO, InterfaceDO interfaceDO){
        CaseModule caseModule = new CaseModule();
        BeanUtils.copyProperties(interfaceDO,caseModule);
        BeanUtils.copyProperties(caseDO,caseModule);
        return caseModule;
    }

    public CaseModule coverCaseModuleFromCase(CaseDO caseDO){
        CaseModule caseModule = new CaseModule();
        BeanUtils.copyProperties(caseDO,caseModule);
        return caseModule;
    }

    public CaseDO coverCaseDOFromCaseModule(CaseModule caseModule){
        CaseDO caseDO = new CaseDO();
        InterfaceDO interfaceDO = this.coverInterfaceDOFromCaseModule(caseModule);
        BeanUtils.copyProperties(caseModule,caseDO);
        log.info(caseDO.toString());
        Integer interfaceId = interfaceDOMapper.selectUniqueInterface(interfaceDO.getProjectName(),
                interfaceDO.getModuleName(),interfaceDO.getInterfaceName());
        log.info("接口ID为："+interfaceId);
        caseDO.setInterfaceId(interfaceId);
        return caseDO;
    }

    public InterfaceDO coverInterfaceDOFromCaseModule(CaseModule caseModule){
        InterfaceDO interfaceDO = new InterfaceDO();
        BeanUtils.copyProperties(caseModule,interfaceDO);
        log.info(interfaceDO.toString());
        return interfaceDO;
    }

}
