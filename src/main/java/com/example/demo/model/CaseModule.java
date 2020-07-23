package com.example.demo.model;

import lombok.Data;

@Data
public class CaseModule {
    private Integer id;
    private String projectName;
    private String moduleName;
    private String interfaceName;
    private String path;
    private String requestMethod;
    private String parameterType;
    private String headerValue;
    private String bodyValue;
    private String variableList;
    private String expectList;
    private String result;
    private String status;
    private String caseType;
    private String description;
}
