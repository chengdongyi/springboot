package com.example.domain;

import lombok.Data;

@Data
public class User {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private String gender;

}
