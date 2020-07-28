package com.example.demo.controller;

import com.example.demo.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class CollectionController {

    @Autowired
    CollectionService collectionService;


    @RequestMapping(value = "/executecollection" ,method = RequestMethod.POST,consumes = "application/json")
    public void executeCollection (@RequestBody List<Integer> collectionId){
        collectionService.executeCollection(collectionId);
    }

}
