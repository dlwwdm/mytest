package com.example.demo.utils;

import com.example.demo.dao.VariableConfigDOMapper;
import com.example.demo.dataobject.VariableConfigDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ReplaceParameter {
    @Autowired
    VariableConfigDOMapper variableConfigDOMapper;
    public String replaceParameter(String string,Integer collectionId){
        List<VariableConfigDO> variableConfigDOS = variableConfigDOMapper.selectByCollectionId(collectionId);
        Map<String,String> collectionVariable = new HashMap();
        for (VariableConfigDO variableConfigDO: variableConfigDOS ) {
            collectionVariable.put(variableConfigDO.getVariableName(),variableConfigDO.getVariableValue());
        }

        if (!string.isEmpty()){
            String reg = "\\$\\{.*?}";
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(string);
            // 遍历替换所有的变量
            while (m.find()){
                String key = m.group().replace("${","").replace("}","");
                if (collectionVariable.containsKey(key)){
                    string = string.replace(m.group(),collectionVariable.get(key));
                }
            }
            return string;
        } else {
            return "";
        }
    }
}
