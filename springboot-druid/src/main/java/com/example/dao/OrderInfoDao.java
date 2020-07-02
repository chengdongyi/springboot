package com.example.dao;

import com.example.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderInfoDao {

    @Select("select id, name, mobile, age, email where user_info where id = #{uid}")
    UserInfo findByUserId(@Param("uid") Integer uid);

}
