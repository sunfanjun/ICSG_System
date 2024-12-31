package com.zjut.springbootprj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image implements Serializable {
    //摄像头编号
    private Integer id;

    //图片地址
    private String link;

    //危险
    private Danger danger;
}

