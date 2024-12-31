package com.zjut.springbootprj.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appeal implements Serializable {
    //申诉id
    private Integer id;

    //申诉时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime time;

    //申诉原因
    private String reason;

    //处理状态
    private Integer status;

    //申诉事件
    private Danger danger;
}
