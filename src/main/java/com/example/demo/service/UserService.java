package com.example.demo.service;

import com.example.demo.dataobject.UserDO;
import com.example.demo.dataobject.UserPasswordDO;
import com.example.demo.model.UserModel;
import org.springframework.stereotype.Service;


public interface UserService {
        //UserModel getUserById(Integer id);

       // Integer getUserIdByName(String name);

        void register(UserModel userModel);


}
