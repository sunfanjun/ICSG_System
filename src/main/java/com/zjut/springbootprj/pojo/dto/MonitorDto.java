package com.zjut.springbootprj.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonitorDto {
    //摄像头编号
    private Integer id;

    //摄像头状态
    private Integer status;

    //发生区域
    @JsonProperty(value = "aId")
    private Integer aId;
}
