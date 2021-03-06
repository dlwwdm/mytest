package com.example.demo.dao;

import com.example.demo.dataobject.ProjectDO;

import java.util.List;

public interface ProjectDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_project
     *
     * @mbg.generated Fri Jun 26 20:00:44 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_project
     *
     * @mbg.generated Fri Jun 26 20:00:44 CST 2020
     */
    int insert(ProjectDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_project
     *
     * @mbg.generated Fri Jun 26 20:00:44 CST 2020
     */
    int insertSelective(ProjectDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_project
     *
     * @mbg.generated Fri Jun 26 20:00:44 CST 2020
     */
    ProjectDO selectByPrimaryKey(Integer id);

    ProjectDO selectByModuleNameAndProjectName(String projectName, String moduleName);

    List<ProjectDO> listProject();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_project
     *
     * @mbg.generated Fri Jun 26 20:00:44 CST 2020
     */
    int updateByPrimaryKeySelective(ProjectDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_project
     *
     * @mbg.generated Fri Jun 26 20:00:44 CST 2020
     */
    int updateByPrimaryKey(ProjectDO record);

    List<ProjectDO> selectByProjectName(String projectName);
}
