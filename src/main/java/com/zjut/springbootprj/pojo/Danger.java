package com.zjut.springbootprj.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Danger implements Serializable {

    //危险事件id
    private Integer id;

    //危险事件类型
    private String type;

    //发生事件
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime fTime;

    //摄像头
    private Monitor monitor;

    //处理状态
    private Integer dispose;

    //处理人员
    private SecurityGuard securityGuard;

    //事件状态
    private Integer status;

    //备注
    private String remark;

    //处理时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime cTime;

    //图片
    @JsonIgnoreProperties(value = {"danger"})
    private List<Image> images;
}
