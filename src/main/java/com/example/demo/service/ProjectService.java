package com.example.demo.service;

import com.example.demo.dataobject.ProjectDO;

import java.util.List;

public interface ProjectService {

    List<ProjectDO> listProject();

    ProjectDO addProject(ProjectDO projectDO);

    ProjectDO editProject(ProjectDO projectDO);

    void deleteProject(Integer id);
}
