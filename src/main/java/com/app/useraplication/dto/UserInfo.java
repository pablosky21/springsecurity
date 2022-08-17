package com.app.useraplication.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserInfo {

    private String name;
    private String username;
    private String email;
    private String phone;

}
