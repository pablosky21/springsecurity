package com.app.useraplication.service;

import java.util.List;

import com.app.useraplication.dto.ParamCounterResponse;
import com.app.useraplication.dto.UserInfo;


public interface UserService {
    
    public List<UserInfo> getAllUser() throws Exception;
    
    public void createUser(UserInfo userInfo) throws Exception;
    
    public void deleteUser(int idUser) throws Exception;
    
    public UserInfo getUser(int idUser) throws Exception;
    
    public ParamCounterResponse getParamCounter(String param) throws Exception;
    
    
    
}
