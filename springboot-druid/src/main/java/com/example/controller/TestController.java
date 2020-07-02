package com.example.controller;

import com.example.dao.UserDao;
import com.example.domain.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private UserDao userDao;

    @RequestMapping("/getUser/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userDao.findByUserName(username);
    }

}
