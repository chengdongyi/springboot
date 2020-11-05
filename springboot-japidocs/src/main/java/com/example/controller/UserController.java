package com.example.controller;

import com.example.domain.Login;
import com.example.domain.Result;
import com.example.domain.User;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chengdongyi
 * @description 用户模块
 * @date 2020/11/5 15:58
 */
@RequestMapping("/api/user/")
@RestController
public class UserController {

    /**
     * 登录接口
     * @param login 登录信息
     * @return com.example.domain.Result
     * @author chengdongyi
     * @date 2020/11/5 16:48
     */
    @RequestMapping("/login")
    public Result login(Login login) {
        return null;
    }

    /**
     * 查询用户信息
     * @param userId 用户Id
     * @return com.example.domain.Result<com.example.domain.User>
     * @author chengdongyi
     * @date 2020/11/5 16:53
     */
    @PostMapping("query")
    public Result<User> query(@RequestParam Long userId) {
        return null;
    }

    /**
     * 添加用户信息
     * @param user 用户信息
     * @return com.example.domain.Result
     * @author chengdongyi
     * @date 2020/11/5 16:55
     */
    @PostMapping("add")
    public Result add(@RequestParam User user) {
        return null;
    }

    /**
     * 删除用户信息
     * @description 删除用户信息, 根据用户Id删除
     * @param userId 用户Id
     * @return com.example.domain.Result<com.example.domain.User>
     * @author chengdongyi
     * @date 2020/11/5 16:53
     */
    @PostMapping("delete")
    @ApiDoc(stringResult = "{code: 0, msg: 'success'}")
    public Result delete(@RequestParam Long userId) {
        return null;
    }

}
