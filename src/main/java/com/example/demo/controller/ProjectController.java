package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.ProjectDOMapper;
import com.example.demo.dataobject.ProjectDO;
import com.example.demo.handler.ResponseInfo;
import com.example.demo.service.ProjectService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
@Slf4j
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/listproject" ,method = RequestMethod.GET)
    public ResponseInfo listProject(){
        List<ProjectDO> projectDOList = projectService.listProject();
        return new ResponseInfo(projectDOList);
    }

//    }
    @RequestMapping(value = "/addproject" ,method = RequestMethod.POST,consumes = {"application/json"})
    public ResponseInfo addProject(@RequestBody JSONObject jsonObject){
        ProjectDO projectDO = new ProjectDO();
        projectDO.setProjectName(jsonObject.getString("projectName"));
        projectDO.setModuleName(jsonObject.getString("moduleName"));
        projectDO.setIpAddress(jsonObject.getString("ipAddress"));
        projectDO.setDescription(jsonObject.getString("description"));
        return new ResponseInfo(projectService.addProject(projectDO));
    }

    @RequestMapping(value = "/editproject", method = RequestMethod.POST,consumes = {"application/json"})
    public ResponseInfo editProject(@RequestBody JSONObject jsonObject){
        ProjectDO projectDO = JSONObject.toJavaObject(jsonObject,ProjectDO.class);
        return new ResponseInfo(projectService.editProject(projectDO));
    }

    @RequestMapping(value = "/deleteproject",method = RequestMethod.GET)
    public ResponseInfo deleteProject(@RequestParam Integer id){
        projectService.deleteProject(id);
        return new ResponseInfo();
    }

    @RequestMapping(value = "/selectprojectbyid",method = RequestMethod.GET)
    public ResponseInfo selectProjectById(@RequestParam Integer id){
        return new ResponseInfo(projectService.selectByPrimaryKey(id));
    }

    @RequestMapping(value = "/selectbyprojectname",method = RequestMethod.GET)
    public ResponseInfo selectProjectByName(@RequestParam String projectName){
        return new ResponseInfo(projectService.selectByProjectName(projectName));
    }

    @RequestMapping(value = "/selectbyprojectandmodule",method = RequestMethod.GET)
    public ResponseInfo selectByProjectAndModule(@RequestParam String projectName, String moduleName){
        return new ResponseInfo(projectService.selectByModuleNameAndProjectName(projectName,moduleName));
    }
}
