package com.example.dao;


import com.example.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select id, username, password, status from user " +
            "where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.example.dao.RoleDao.findByUId")
            )
    })
    User findByUserName(@Param("username") String username);

}


