package com.example.dao;

import com.example.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    String SELECT = "select id, username, password, status from user ";

    @Select(SELECT + "where username = #{username}")
    User findByUserName(@Param("username") String username);

    /**
     * 关系: 一对一
     * 表：user 和 user_info
     */
    @Select(SELECT + "where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userInfo", column = "id",
                    one = @One(select = "com.example.dao.UserInfoDao.findByUserId")
            )
    })
    User findUserAndUserInfoByUserName(@Param("username") String username);

    /**
     * 关系: 一对多
     * 表：user 和 role
     */
    @Select(SELECT + "where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType =List.class,
                    many = @Many(select = "com.example.dao.RoleDao.findByUId")
            )
    })
    User findUserAndRoleByUserName(@Param("username") String username);

    /**
     * 关系: 一对一、一对多
     * 表：user、user_info 和 role
     */
    @Select(SELECT + "where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "userInfo", column = "id",
                    one = @One(select = "com.example.dao.UserInfoDao.findByUserId")
            ),
            @Result(property = "roles", column = "id", javaType =List.class,
                    many = @Many(select = "com.example.dao.RoleDao.findByUId")
            )
    })
    User findByName(@Param("username") String username);
}
