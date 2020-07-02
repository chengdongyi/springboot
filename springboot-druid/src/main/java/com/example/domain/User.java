package com.example.domain;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private Integer status;

    private List<Role> roles;

    private UserInfo userInfo;

}
