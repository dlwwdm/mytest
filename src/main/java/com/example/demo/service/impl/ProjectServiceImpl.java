package com.example.demo.service.impl;

import com.example.demo.dao.ProjectDOMapper;
import com.example.demo.dataobject.ProjectDO;
import com.example.demo.handler.BusinessErrorException;
import com.example.demo.handler.BusinessMsgEnum;
import com.example.demo.service.ProjectService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectDOMapper projectDOMapper;

    @Override
    public List<ProjectDO> listProject() {
        List<ProjectDO> projectDOList = projectDOMapper.listProject();
        return projectDOList;
    }

    @Override
    public ProjectDO addProject(ProjectDO projectDO) {
       ProjectDO  projectDOExist = projectDOMapper.selectByModuleNameAndProjectName(projectDO);
        if(projectDO == null){
            throw new BusinessErrorException(BusinessMsgEnum.PARAMETER_VALIDATION_EXCEPTION);
        }
       if(projectDOExist != null){
           throw new BusinessErrorException(BusinessMsgEnum.PROJECT_HAS_EXIST);
       }
        projectDOMapper.insertSelective(projectDO);
       return projectDOMapper.selectByPrimaryKey(projectDO.getId());
    }

    @Override
    public ProjectDO editProject(ProjectDO projectDO) {
        projectDOMapper.updateByPrimaryKeySelective(projectDO);
        return projectDOMapper.selectByPrimaryKey(projectDO.getId());
    }

    @Override
    public void deleteProject(Integer id) {
        log.info("要删除的ID为："+id);
        projectDOMapper.deleteByPrimaryKey(id);
    }
}
