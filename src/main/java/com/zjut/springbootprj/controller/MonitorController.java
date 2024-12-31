package com.zjut.springbootprj.controller;

import com.github.pagehelper.PageInfo;
import com.zjut.springbootprj.common.PageResult;
import com.zjut.springbootprj.common.Result;
import com.zjut.springbootprj.pojo.Danger;
import com.zjut.springbootprj.pojo.Monitor;
import com.zjut.springbootprj.pojo.dto.MonitorPageDto;
import com.zjut.springbootprj.service.MonitorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
    @Resource
    private MonitorService monitorService;

    @PostMapping("/find")
    public Result findMonitor(@RequestBody MonitorPageDto monitorPageDto){
        List<Monitor> monitorList=monitorService.findMonitor(monitorPageDto);
        PageInfo<Monitor> pageInfo=new PageInfo<>(monitorList);
        return Result.success(new PageResult<Monitor>(pageInfo.getTotal(),pageInfo.getList()));
    }

    @PostMapping
    public Result saveMonitor(@RequestBody Monitor monitor){
        if(monitorService.saveMonitor(monitor))
            return Result.success();
        else
            return Result.error("数据插入失败！");
    }

    @PutMapping
    public Result updateMonitor(@RequestBody Monitor monitor){
        if(monitorService.updateMonitor(monitor))
            return Result.success();
        else
            return Result.error("数据更新失败！");
    }

    @DeleteMapping
    public Result deleteMonitor(@RequestParam List<Integer> ids){
        if(monitorService.deleteMonitors(ids))
            return Result.success();
        else
            return Result.error("数据删除失败！");
    }
}
