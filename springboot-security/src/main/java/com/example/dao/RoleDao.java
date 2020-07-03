package com.example.dao;

import com.example.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleDao {

    @Select("select id, rolename, roledesc from role where id = #{uid}")
    List<Role> findByUId(@Param("uid") Integer uid);

}
