package com.example.dao;


import com.example.domain.User;

public interface UserDao {

//    @Select("select * from user where username = #{username}")
//    @Results({
//            @Result(id = true, property = "id", colomn = "id"),
//            @Result(property = "roles", colomn = "id", javaType =List.class,
//                    many = @Many(select = "com.example.dao.UserDao.findByUserId")
//            )
//    })
    User findByName(String username);

}


