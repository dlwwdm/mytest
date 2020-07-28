package com.example.demo.dao;

import com.example.demo.dataobject.CollectionDO;

public interface CollectionDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_collection
     *
     * @mbg.generated Sat Jul 25 11:21:13 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_collection
     *
     * @mbg.generated Sat Jul 25 11:21:13 CST 2020
     */
    int insert(CollectionDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_collection
     *
     * @mbg.generated Sat Jul 25 11:21:13 CST 2020
     */
    int insertSelective(CollectionDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_collection
     *
     * @mbg.generated Sat Jul 25 11:21:13 CST 2020
     */
    CollectionDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_collection
     *
     * @mbg.generated Sat Jul 25 11:21:13 CST 2020
     */
    int updateByPrimaryKeySelective(CollectionDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_collection
     *
     * @mbg.generated Sat Jul 25 11:21:13 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(CollectionDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_collection
     *
     * @mbg.generated Sat Jul 25 11:21:13 CST 2020
     */
    int updateByPrimaryKey(CollectionDO record);
}