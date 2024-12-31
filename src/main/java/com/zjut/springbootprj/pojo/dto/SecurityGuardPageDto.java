package com.zjut.springbootprj.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityGuardPageDto {
    //页码
    private Integer pageNum;

    //每页显示条数
    private Integer pageSize;

    //SecurityGuard查询实体类
    private SecurityGuardDto securityGuardDto;
}
