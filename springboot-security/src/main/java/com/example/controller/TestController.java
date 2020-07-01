package com.example.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Secured("user") // 需要用户角色
    @RequestMapping("/test")
    public String test() {


        return "12345";
    }



}
