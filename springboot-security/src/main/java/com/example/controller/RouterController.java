package com.example.controller;

import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

    @RequestMapping({"/","/index"})
    public String index(){
        return  "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return  "views/login";
    }

    @RequestMapping("/tourist/{id}")
    public  String tourist(@PathVariable("id") int id){
        return "views/tourist/"+id;
    }

    @Secured("ROLE_user")
    @RequestMapping("/user/{id}")
    public  String user(@PathVariable("id") int id){
        return "views/user/"+id;
    }

    @Secured("ROLE_vip")
    @RequestMapping("/vip/{id}")
    public  String vip(@PathVariable("id") int id){
        return "views/vip/"+id;
    }

    @Secured("ROLE_svip")
    @RequestMapping("/svip/{id}")
    public  String svip(@PathVariable("id") int id){
        return "views/svip/"+id;
    }

}
