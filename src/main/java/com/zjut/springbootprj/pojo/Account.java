package com.zjut.springbootprj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    //用户编号
    private Integer id;

    //账号
    private String username;

    //密码
    private String password;

    //用户身份类型
    private Integer type;

    //对应保安人员
    private SecurityGuard securityGuard;
}
