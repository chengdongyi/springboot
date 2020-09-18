package com.example.controller;

import com.example.domain.Result;
import com.example.domain.Save;
import com.example.domain.Update;
import com.example.domain.User;
import com.example.enums.ReturnCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@RestController
@RequestMapping("/validator")
public class ValidatorController {

    @Resource
    private Validator validator;

    // ============================= @PathVariable、@RequestParam =============================

    @RequestMapping("/test1")
    public Result test2(@NotNull(message = "不能为空") String username, @Length(min = 6, message = "长度不小于6") String password) {
        System.out.println("username: " + username + ", password:" + password);
        return Result.success();
    }

    @RequestMapping("/login2/{username}/{password}")
    public Result login2(@PathVariable("username") String username, @PathVariable("password") @Length(min = 6, message = "长度不小于6") String password) {
        System.out.println("username: " + username + ", password:" + password);
        return Result.success();
    }

    @RequestMapping("/login3")
    public Result login3(@NotBlank(message = "不能为空") @RequestParam("username") String username, @Length(min = 6, message = "长度不小于6") @RequestParam("password") String password) {
        System.out.println("username: " + username + ", password:" + password);
        return Result.success();
    }

    // ====================================== @RequestBody ======================================

    @PostMapping("/login")
    public Result login(@RequestBody @Validated User user) {
        System.out.println(user);
        return Result.success();
    }

    @PostMapping("/save")
    public Result save(@RequestBody @Validated(Save.class) User user) {
        System.out.println(user);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Validated(Update.class) User user) {
        System.out.println(user);
        return Result.success();
    }

    // ======================================== 表单校验 ========================================

    @PostMapping("/login1")
    public Result login1(@Validated User user) {
        System.out.println(user);
        return Result.success();
    }

    @PostMapping("/login2")
    public Result login2(@Valid User user) {
        System.out.println(user);
        return Result.success();
    }

    // ======================================= 编程式校验 =======================================

    @PostMapping("/updateUser")
    public Result saveWithCodingValidate(@RequestBody User user) {

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user, Update.class);

        if (!constraintViolations.isEmpty()) {
            StringBuffer sb = new StringBuffer(ReturnCode.PARAM_ERROR.message());
            for (ConstraintViolation<User> constrain : constraintViolations) {
                sb.append(constrain.getPropertyPath()).append(": ").append(constrain.getMessage()).append("; ");
            }
            return Result.fail(ReturnCode.PARAM_ERROR, sb.toString());
        }
        return Result.success();
    }

}
