package com.zjut.springbootprj.controller;

import com.github.pagehelper.PageInfo;
import com.zjut.springbootprj.common.PageResult;
import com.zjut.springbootprj.common.Result;
import com.zjut.springbootprj.pojo.Area;
import com.zjut.springbootprj.pojo.Danger;
import com.zjut.springbootprj.pojo.dto.AreaPageDto;
import com.zjut.springbootprj.service.AreaService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/area")
public class AreaController {
    @Resource
    private AreaService areaService;

    @GetMapping
    public Result findAll(){
        return Result.success(areaService.findAll());
    }

    @PostMapping("/find")
    public Result findArea(@RequestBody AreaPageDto areaPageDto){
        List<Area> areaList=areaService.findArea(areaPageDto);
        PageInfo<Area> pageInfo=new PageInfo<>(areaList);
        return Result.success(new PageResult<Area>(pageInfo.getTotal(),pageInfo.getList()));
    }

    @PostMapping
    public Result saveArea(@RequestBody Area Area){
        if(areaService.saveArea(Area))
            return Result.success();
        else
            return Result.error("数据插入失败！");
    }

    @PutMapping
    public Result updateArea(@RequestBody Area Area){
        if(areaService.updateArea(Area))
            return Result.success();
        else
            return Result.error("数据更新失败！");
    }

    @DeleteMapping
    public Result deleteMonitor(@RequestParam List<Integer> ids){
        if(areaService.deleteAreas(ids))
            return Result.success();
        else
            return Result.error("数据删除失败！");
    }
}
