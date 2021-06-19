package com.example.demo.controller;

import com.example.demo.service.CollectionService;
import com.example.demo.utils.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class CollectionController {

    @Autowired
    CollectionService collectionService;


    @RequestMapping(value = "/executecollection" ,method = RequestMethod.POST,consumes = "application/json")
    public void executeCollection (@RequestBody List<Integer> collectionId){
        collectionService.executeCollection(collectionId);

    }

}
