package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.handler.GlobalExceptionHandler;
import com.example.demo.handler.ResponseInfo;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import lombok.extern.log4j.Log4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class UserController extends GlobalExceptionHandler {

    @Autowired
    UserService userService;

    //用户注册
    @RequestMapping(value = "/register",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    @Transactional
    public ResponseInfo registerUser(@RequestBody JSONObject jsonObject) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UserModel userModel = new UserModel();
        userModel.setEncrptPassword(this.EncodeByMD5(jsonObject.getString("encrptPassword")));
        userModel.setName(jsonObject.getString("name"));
        userModel.setEmail(jsonObject.getString("email"));
        userModel.setGender(jsonObject.getString("gender"));
        userModel.setTelphone(jsonObject.getString("telphone"));
        userService.register(userModel);
        return new ResponseInfo(userModel);
    }

    public String EncodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String newStr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newStr;
    }
    //根据id查询用户
    @RequestMapping(value = "/getuserbyid",method = RequestMethod.GET)
    public ResponseInfo getUserById(@RequestParam(name ="id")Integer id){
        UserModel userModel = userService.getUserById(id);
        return new ResponseInfo(userModel);
    }

    //查询所有用户
    @RequestMapping(value = "/listuser" ,method = RequestMethod.GET)
    public ResponseInfo listUser(){
        List<UserModel> userModelList = userService.listUser();
        return new ResponseInfo(userModelList);
    }

    //用户登录
    @RequestMapping(value = "/login",method = RequestMethod.POST,consumes = "application/json")
    public ResponseInfo login(@RequestBody JSONObject jsonObject) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UserModel userModel = new UserModel();
        userModel.setName(jsonObject.getString("name"));
        userModel.setEncrptPassword(this.EncodeByMD5(jsonObject.getString("encrptPassword")));
        userService.login(userModel);
        return new ResponseInfo(userModel);
    }
}
