package com.example.demo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.dao.UserDOMapper;
import com.example.demo.dao.UserPasswordDOMapper;
import com.example.demo.dataobject.UserDO;
import com.example.demo.dataobject.UserPasswordDO;
import com.example.demo.handler.BusinessErrorException;
import com.example.demo.handler.BusinessMsgEnum;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDOMapper userDOMapper;

    @Autowired
    UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);
        UserModel userModel =  covertUserModelFromUserDo(userDO,userPasswordDO);
        return userModel;
    }

    /*
    * 用户注册
    * */
    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessErrorException{
        UserDO userDo = userDOMapper.selectByUserName(userModel.getName());
        if(userModel == null){
            throw new BusinessErrorException(BusinessMsgEnum.PARAMETER_VALIDATION_EXCEPTION);
        }
        if(StringUtils.isEmpty(userModel.getName())
                ||StringUtils.isEmpty(userModel.getEncrptPassword())){
            throw new BusinessErrorException(BusinessMsgEnum.PARAMETER_VALIDATION_EXCEPTION);
        }
        if(userDo != null){
            throw new BusinessErrorException(BusinessMsgEnum.USER_HAS_REGISTER);
        }
        UserDO userDO = coverUserDoFromUserModel(userModel);
        userDOMapper.insertSelective(userDO);
        userModel.setId(userDO.getId());
        UserPasswordDO userPasswordDO = coverPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
        return ;
    }

    @Override
    public List<UserModel> listUser() {
        List<UserDO> userDOList = userDOMapper.listUser();
        List<UserModel> userModelList = userDOList.stream().map(userDO -> {
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = this.covertUserModelFromUserDo(userDO,userPasswordDO);
        return userModel;
        }).collect(Collectors.toList());
        return userModelList;
    }


    /*
     * 用户登录
     * */
    @Override
    public void login(UserModel userModel) {
        UserDO userDO = userDOMapper.selectByUserName(userModel.getName());
        if(userDO == null){
            throw new BusinessErrorException(BusinessMsgEnum.USER_NOT_EXIST);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        System.out.println(userPasswordDO.getEncrptPassword());
        System.out.println(userModel.getEncrptPassword());
        if(!(userPasswordDO.getEncrptPassword().equals(userModel.getEncrptPassword()))){
            throw new BusinessErrorException(BusinessMsgEnum.USER_PASSWORD_NOT_EQUALS);
        }

    }

    private UserDO coverUserDoFromUserModel (UserModel userModel){
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel ,userDO);
        return userDO;
    }

    private UserPasswordDO coverPasswordFromModel(UserModel userModel){
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }


    private UserModel covertUserModelFromUserDo(UserDO userDO ,UserPasswordDO userPasswordDO){
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        return userModel;
    }
}
