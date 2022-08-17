package com.app.useraplication.services;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.app.useraplication.dto.UserInfo;
import com.app.useraplication.entity.UserEntity;
import com.app.useraplication.repository.UserRepository;
import com.app.useraplication.service.UserService;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Test
    public void testGetAllUser() throws Exception {

        List<UserEntity> listUserDetail = new ArrayList<UserEntity>();
        UserEntity userDetail = UserEntity.builder().name("pablo").email("pablo@gmail.com")
                .phone("456665").username("pablouser").build();
        listUserDetail.add(userDetail);
        when(userRepository.findAll()).thenReturn(listUserDetail);
        List<UserInfo> listaUsuarios = userService.getAllUser();
        assertFalse(listaUsuarios.isEmpty());
    }

    @Test
    public void testGetUser() throws Exception {

        UserEntity userDetail = UserEntity.builder().name("pablo").email("pablo@gmail.com")
                .phone("456665").username("pablouser").build();
        when(userRepository.getOne(1)).thenReturn(userDetail);
        UserInfo userEntity = userService.getUser(1);
        assertFalse(userEntity == null);
    }

    @Test
    void createUser() throws Exception {
        UserEntity userDetail = UserEntity.builder().name("pablo").email("pablo@gmail.com")
                .phone("456665").username("pablouser").build();
        UserInfo productoDto = UserInfo.builder().name("pablo").email("pablo@gmail.com")
                .phone("456665").username("pablouser").build();
        when(userRepository.save(any(UserEntity.class))).thenReturn(userDetail);
        userService.createUser(productoDto);

    }
    

}
