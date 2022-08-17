package com.app.useraplication.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.useraplication.dto.ParamCounterResponse;
import com.app.useraplication.dto.UserInfo;
import com.app.useraplication.service.UserService;

@RestController
@RequestMapping("/")

public class UserController {

    @Autowired
    private UserService transactionUserService;

    private static final Logger logger = LoggerFactory.getLogger("UserInfoPointsController");

    @GetMapping(value = "getAllUser")
    public ResponseEntity<List<UserInfo>> getAllUser() {
        try {
            return new ResponseEntity<>(transactionUserService.getAllUser(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getAllUser-----", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping(value = "createUser")
    public ResponseEntity<List<UserInfo>> createUser(@RequestBody UserInfo userInfo) {
        try {
            transactionUserService.createUser(userInfo);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("createUser-----", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping(value = "deleteUser/{id}")
    public ResponseEntity<List<UserInfo>> deleteUser(@PathVariable("id") int idUser) {
        try {
            transactionUserService.deleteUser(idUser);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("deleteUser-----", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(value = "getUser/{id}")
    public ResponseEntity<UserInfo> getUser(@PathVariable("id") int idUser) {
        try {
            return new ResponseEntity<>(transactionUserService.getUser(idUser), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getUser-----", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(value = "getParamCounter/{param}")
    public ResponseEntity<ParamCounterResponse> getParamCounter(@PathVariable("param") String param) {
        try {
            return new ResponseEntity<>(transactionUserService.getParamCounter(param), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getParamCounter-----", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
