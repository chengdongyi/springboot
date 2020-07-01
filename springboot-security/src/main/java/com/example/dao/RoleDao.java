package com.example.dao;

import com.example.domain.Role;

import java.util.List;

public interface RoleDao {

//    @Select("select * from role where id = #{id}")
    List<Role> findByUserId(Integer id);

}
