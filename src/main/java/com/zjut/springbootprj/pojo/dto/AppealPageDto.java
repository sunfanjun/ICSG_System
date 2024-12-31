package com.zjut.springbootprj.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppealPageDto {
    //页码
    private Integer pageNum;

    //每页显示条数
    private Integer pageSize;

    //Appeal查询实体类
    private AppealDto appealDto;
}
