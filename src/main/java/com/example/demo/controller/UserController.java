package com.example.demo.controller;

import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;



    @RequestMapping(value = "/register" ,method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public UserModel registerUser(@RequestParam(name = "name")String name,
                             @RequestParam(name = "telphone")String telphone,
                             @RequestParam(name = "email")String email,
                             @RequestParam(name = "gender")String gender,
                             @RequestParam(name = "encrptPassword")String encrptPassword){

//        UserDO userDo = new UserDO();
//        UserPasswordDO userPasswordDO = new UserPasswordDO();
//        userDo.setEmail(email);
//        userDo.setGender(gender);
//        userDo.setName(name);
//        userDo.setTelphone(telphone);
//        userService.addUser(userDo);
//        userService.getUserIdByName(name);
//        userPasswordDO.setEncrptPassword(encrptPassword);
//        userPasswordDO.setUserId(userService.getUserIdByName(name));
//        userPasswordService.addUserPassword(userPasswordDO);
//        return null;
        UserModel userModel = new UserModel();
        userModel.setEncrptPassword(encrptPassword);
        userModel.setName(name);
        userModel.setEmail(email);
        userModel.setGender(gender);
        userService.register(userModel);

        return null;
    }

//    @RequestMapping(value = "/getuserbyid",method = RequestMethod.GET)
//    @ResponseBody

//    public UserModel getUserById(@RequestParam(name ="id")Integer id){
//        UserModel userModel = userService.getUserById(id);
//        return userModel;
//    }
}
