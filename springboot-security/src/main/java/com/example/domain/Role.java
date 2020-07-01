package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author chengdongyi
 * @description:
 * @date 2020/7/1 20:58
 */
public class Role implements GrantedAuthority {

    private Integer id;

    private String rolename;

    private  String roledesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return rolename;
    }
}
