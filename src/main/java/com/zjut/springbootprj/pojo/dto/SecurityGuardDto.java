package com.zjut.springbootprj.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityGuardDto {
    //保安姓名
    private String name;

    //保安性别
    private Integer sex;

    //保安职务
    private String level;

    //保安管理区域
    @JsonProperty(value = "aId")
    private Integer aId;
}
