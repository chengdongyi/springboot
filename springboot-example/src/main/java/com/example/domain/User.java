package com.example.domain;

import com.example.annotation.FixedValue;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class User {

    @NotNull(message = "不能为空", groups = {Update.class})
    private Integer id;

    @NotBlank(message = "不能为空", groups = {Save.class, Update.class})
    private String username;

    @NotNull(message = "不能为空", groups = {Save.class, Update.class})
    @Length(min = 6, max = 20, message = "长度在6-20之间", groups = {Save.class, Update.class})
    private String password;

    @FixedValue(params = {"18", "20"})
    private String age;

    @Valid
    private List<Order> order;

}
