package com.zjut.springbootprj.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppealDto {
    //申诉时间
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private LocalDate time;

    //事件发生区域
    @JsonProperty(value = "aId")
    private Integer aId;
    //事件类型
    private String type;
    //摄像头编号
    @JsonProperty(value = "mId")
    private Integer mId;
    //处理人员名字
    @JsonProperty(value = "pName")
    private String pName;

    private  Integer status;
}
