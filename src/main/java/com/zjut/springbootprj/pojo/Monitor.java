package com.zjut.springbootprj.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monitor implements Serializable {
    //摄像头id
    private Integer id;

    //发生区域
    private Area area;

    //开始使用时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate time;

    //使用状态
    private Integer status;

}
