package com.example.dao;

import com.example.domain.OrderInfo;
import com.example.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoDao {

    @Select("select id, name, mobile, age, email from user_info where id = #{uid}")
    UserInfo findByUserId(@Param("uid") Integer uid);

}
