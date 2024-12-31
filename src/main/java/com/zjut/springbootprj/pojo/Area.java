package com.zjut.springbootprj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Area implements Serializable {
    //区域编号
    private Integer id;

    //区域名称
    private String name;
}
