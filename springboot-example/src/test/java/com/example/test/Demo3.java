package com.example.test;

import lombok.Data;

/**
 * 计算表达式
 */
public class Demo3 {

    public static void main(String[] args) {

        User user = new User();
        user.setUserName("张三");
        user.setPassword("123456");
        user.setUserType("员工");
        login(user);
    }

    private static void login(User user) {

        String userType = user.getUserType();

        switch (userType) {
            case "超级管理员":
                System.out.println("超级管理员业务逻辑");
                break;

            case "董事长":
                System.out.println("董事长业务逻辑");
                break;
            case "经理":
                System.out.println("经理业务逻辑");
                break;
            case "员工":
                System.out.println("员工业务逻辑");
                break;
        }

    }
}

@Data
class User {
    private String userName;
    private String password;
    private String userType;
}
