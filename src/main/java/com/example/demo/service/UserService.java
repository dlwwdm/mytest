package com.example.demo.service;

import com.example.demo.dataobject.UserDO;
import com.example.demo.dataobject.UserPasswordDO;
import com.example.demo.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
        UserModel getUserById(Integer id);

       // Integer getUserIdByName(String name);

        void register(UserModel userModel);

        List<UserModel> listUser();

        void login(UserModel userModel);
}
