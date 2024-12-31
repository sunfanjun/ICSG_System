package com.zjut.springbootprj.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements Serializable {

    //账号
    @JsonProperty(value = "username")
    private String username;

    //密码
    @JsonProperty(value = "password")
    private String password;

    private String token;
}
