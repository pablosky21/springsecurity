package com.app.useraplication.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.useraplication.dto.ParamCounterResponse;
import com.app.useraplication.dto.UserInfo;
import com.app.useraplication.entity.UserEntity;
import com.app.useraplication.repository.UserRepository;
import com.app.useraplication.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url.location}")
    private String API_URL_LOCATION;

    @Override
    public List<UserInfo> getAllUser() throws Exception {

        List<UserInfo> UserInfoList = new ArrayList<>();
        try {
            List<UserEntity> listTransactionUserDto = userRepository.findAll();

            UserInfoList = listTransactionUserDto.stream().map(userEntity -> {
                UserInfo userInfo = UserInfo.builder().email(userEntity.getEmail()).name(userEntity.getName())
                        .phone(userEntity.getPhone()).username(userEntity.getUsername()).build();
                return userInfo;
            }).collect(Collectors.toList());

        } catch (Exception e) {
            throw new Exception("Server error " + e.getMessage());
        }

        return UserInfoList;
    }

    @Override
    public void createUser(UserInfo userInfo) throws Exception {

        UserEntity userEntity =
                UserEntity.builder().email(userInfo.getEmail()).name(userInfo.getName()).phone(userInfo.getPhone())
                        .username(userInfo.getName()).build();
        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(int idUser) throws Exception {
        userRepository.deleteById(idUser);

    }

    @Override
    public UserInfo getUser(int idUser) throws Exception {
        UserEntity userEntity = userRepository.getOne(idUser);
        UserInfo userInfo = UserInfo.builder().email(userEntity.getEmail()).name(userEntity.getName())
                .phone(userEntity.getPhone()).username(userEntity.getUsername()).build();
        return userInfo;
    }

    @Override
    public ParamCounterResponse getParamCounter(String param) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("param", param);
        ResponseEntity<ParamCounterResponse> locationReponse = null;

        try {

            HttpHeaders httpHeaders = getHeaders();

            HttpEntity<?> entity = new HttpEntity<Object>(httpHeaders);

            DESKeySpec keySpec = new DESKeySpec("ionix123456".getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            byte[] cleartext = param.getBytes("UTF8");
            Cipher cipher = Cipher.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(keySpec);
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            String encryptedRut = new String(Base64.getUrlEncoder().encodeToString(cipher.doFinal(cleartext)));
            long startTime = System.currentTimeMillis();
            locationReponse =
                    restTemplate.exchange(API_URL_LOCATION + encryptedRut,
                            HttpMethod.GET,
                            entity, ParamCounterResponse.class);
            long duration = System.currentTimeMillis() - startTime;
            locationReponse.getBody().getResult()
                    .setRegisterCount(locationReponse.getBody().getResult().getItems().size());
            locationReponse.getBody().setElapsedTime(duration);

        } catch (Exception e) {
            throw new Exception("Server error " + e.getMessage());
        }
        return locationReponse.getBody();
    }

    private HttpHeaders getHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-Key", "f2f719e0");
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        headers.setAccept(acceptableMediaTypes);
        return headers;
    }

}
