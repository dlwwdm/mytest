package com.example.demo.service.impl;

import com.example.demo.dao.UserDOMapper;
import com.example.demo.dao.UserPasswordDOMapper;
import com.example.demo.dataobject.UserDO;
import com.example.demo.dataobject.UserPasswordDO;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDOMapper userDOMapper;

    @Autowired
    UserPasswordDOMapper userPasswordDOMapper;

//    @Override
//    public UserModel getUserById(Integer id) {
//        UserDO userDO = userDOMapper.selectByUserId(id);
//        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);
//        UserModel userModel =  covertUserModelFromUserDo(userDO,userPasswordDO);
//        return userModel;
//    }


    @Override
    @Transactional
    public void register(UserModel userModel) {
        UserDO userDO = coverUserDoFromUserModel(userModel);
        userDOMapper.insertSelective(userDO);
        UserPasswordDO userPasswordDO = coverPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
        return ;
    }

    private UserDO coverUserDoFromUserModel (UserModel userModel){
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel ,userDO);
        return userDO;
    }

    private UserPasswordDO coverPasswordFromModel(UserModel userModel){
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setId(userModel.getId());
        return userPasswordDO;
    }


//    private UserModel covertUserModelFromUserDo(UserDO userDO ,UserPasswordDO userPasswordDO){
//        UserModel userModel = new UserModel();
//        BeanUtils.copyProperties(userDO,userModel);
//        userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
//        return userModel;
//    }
}
