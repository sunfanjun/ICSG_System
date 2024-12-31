package com.zjut.springbootprj.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DangerDto {
    //发生时间
    @JsonProperty(value = "fTime")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private LocalDate fTime;

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

    private Integer dispose;
}
