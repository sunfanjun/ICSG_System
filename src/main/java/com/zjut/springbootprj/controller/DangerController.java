package com.zjut.springbootprj.controller;

import com.github.pagehelper.PageInfo;
import com.zjut.springbootprj.common.PageResult;
import com.zjut.springbootprj.common.Result;
import com.zjut.springbootprj.pojo.Danger;
import com.zjut.springbootprj.pojo.dto.DangerPageDto;
import com.zjut.springbootprj.service.DangerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/danger")
public class DangerController {
    @Resource
    private DangerService dangerService;

    @GetMapping("/status/{status}")
    public Result findAllByStatus(@PathVariable Integer status){
        return Result.success(dangerService.findAllByStatus(status));
    }
    @PostMapping("/find")
    public Result findDanger(@RequestBody DangerPageDto dangerPageDto){
        List<Danger> DangerList=dangerService.findDanger(dangerPageDto);
        PageInfo<Danger> pageInfo=new PageInfo<>(DangerList);
       return Result.success(new PageResult<Danger>(pageInfo.getTotal(),pageInfo.getList()));
    }

    @PostMapping("/findById")
    public Result findDangerById(@RequestParam Integer id){
        Danger danger = dangerService.findDangerById(id);
        return Result.success(danger);
    }

    @PostMapping
    public Result saveDanger(@RequestBody Danger danger){
        if(dangerService.saveDanger(danger))
            return Result.success();
        else
            return Result.error("数据插入失败！");
    }

    @PutMapping
    public Result updateDanger(@RequestBody Danger danger){
        if(dangerService.updateDanger(danger))
            return Result.success();
        else
            return Result.error("数据更新失败！");
    }

    @DeleteMapping
    public Result deleteMonitor(@RequestParam List<Integer> ids){
        if(dangerService.deleteDangers(ids))
            return Result.success();
        else
            return Result.error("数据删除失败！");
    }
}
