package com.zjut.springbootprj.controller;

import com.github.pagehelper.PageInfo;
import com.zjut.springbootprj.common.PageResult;
import com.zjut.springbootprj.common.Result;
import com.zjut.springbootprj.pojo.SecurityGuard;
import com.zjut.springbootprj.pojo.dto.SecurityGuardPageDto;
import com.zjut.springbootprj.service.SecurityGuardService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/securityGuard")
public class SecurityGuardController {
    @Resource
    private SecurityGuardService securityGuardService;

    @PostMapping("/find")
    public Result findSecurityGuard(@RequestBody SecurityGuardPageDto securityGuardPageDto){
        List<SecurityGuard> securityGuardList=securityGuardService.findSecurityGuard(securityGuardPageDto);
        PageInfo<SecurityGuard> pageInfo=new PageInfo<>(securityGuardList);
        return Result.success(new PageResult<SecurityGuard>(pageInfo.getTotal(),pageInfo.getList()));
    }

    @PostMapping
    public Result saveSecurityGuard(@RequestBody SecurityGuard securityGuard){
        if(securityGuardService.saveSecurityGuard(securityGuard))
            return Result.success();
        else
            return Result.error("数据插入失败！");
    }

    @PutMapping
    public Result updateSecurityGuard(@RequestBody SecurityGuard securityGuard){
        if(securityGuardService.updateSecurityGuard(securityGuard))
            return Result.success();
        else
            return Result.error("数据更新失败！");
    }

    @DeleteMapping
    public Result deleteSecurityGuard(@RequestParam List<Integer> ids){
        if(securityGuardService.deleteSecurityGuards(ids))
            return Result.success();
        else
            return Result.error("数据删除失败！");
    }
}
