package com.zjut.springbootprj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityGuard implements Serializable {
    //保安id
    private Integer id;

    //保安姓名
    private String name;

    //保安性别
    private Integer sex;

    //保安联系方式
    private String contact;

    //保安职务
    private String level;

    //保安管理区域
    private Area area;
}
