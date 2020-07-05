package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.ProjectDOMapper;
import com.example.demo.dataobject.ProjectDO;
import com.example.demo.handler.ResponseInfo;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/listproject" ,method = RequestMethod.GET)
    public ResponseInfo listProject(){
        List<ProjectDO> projectDOList = projectService.listProject();
        return new ResponseInfo(projectDOList);
    }

    @RequestMapping(value = "/addproject" ,method = RequestMethod.POST,consumes = {"application/x-www-form-urlencoded"})
    public ResponseInfo addProject(@RequestParam(name="projectName") String projectName,
                                   @RequestParam(name="moduleName") String moduleName,
                                   @RequestParam(name="ipAddress") String ipAddress,
                                   @RequestParam(name="description") String description
                                   ){
        ProjectDO projectDO = new ProjectDO();
        projectDO.setProjectName(projectName);
        projectDO.setModuleName(moduleName);
        projectDO.setIpAddress(ipAddress);
        projectDO.setDescription(description);
        return new ResponseInfo(projectService.addProject(projectDO));
    }

    @RequestMapping(value = "/editproject", method = RequestMethod.POST,consumes = {"application/json"})
    public ResponseInfo editProject(@RequestBody JSONObject jsonObject){
        ProjectDO projectDO = new ProjectDO();
        projectDO.setId(jsonObject.getInteger("id"));
        projectDO.setDescription(jsonObject.getString("description"));
        projectDO.setIpAddress(jsonObject.getString("ipAddress"));
        projectDO.setModuleName(jsonObject.getString("moduleName"));
        projectDO.setProjectName(jsonObject.getString("projectName"));
        return new ResponseInfo(projectService.editProject(projectDO));
    }

    @RequestMapping(value = "/deleteproject",method = RequestMethod.GET)
    public ResponseInfo deleteProject(@RequestParam Integer id){
        projectService.deleteProject(id);
        return new ResponseInfo();
    }
}
