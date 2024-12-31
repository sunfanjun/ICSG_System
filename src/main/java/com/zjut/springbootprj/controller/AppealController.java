package com.zjut.springbootprj.controller;

import com.github.pagehelper.PageInfo;
import com.zjut.springbootprj.common.PageResult;
import com.zjut.springbootprj.common.Result;
import com.zjut.springbootprj.pojo.Appeal;
import com.zjut.springbootprj.pojo.Danger;
import com.zjut.springbootprj.pojo.dto.AppealPageDto;
import com.zjut.springbootprj.service.AppealService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/appeal")
public class AppealController {
    @Resource
    private AppealService appealService;

    @PostMapping("/find")
    public Result findAppeal(@RequestBody AppealPageDto appealPageDto) {
        List<Appeal> AppealList = appealService.findAppeal(appealPageDto);
        PageInfo<Appeal> pageInfo = new PageInfo<>(AppealList);
        return Result.success(new PageResult<Appeal>(pageInfo.getTotal(), pageInfo.getList()));
    }

    @PostMapping("/findById")
    public Result findDangerById(@RequestParam Integer id) {
        Appeal appeal = appealService.findAppealById(id);
        return Result.success(appeal);
    }

    @PostMapping
    public Result saveAppeal(@RequestBody Appeal appeal) {
        if (appealService.saveAppeal(appeal))
            return Result.success();
        else
            return Result.error("数据插入失败！");
    }

    @PutMapping
    public Result updateAppeal(@RequestBody Appeal appeal) {
        if (appealService.updateAppeal(appeal))
            return Result.success();
        else
            return Result.error("数据更新失败！");
    }

    @PutMapping("/{id}")
    public Result updateAppeal(@PathVariable Integer id) {
        if (appealService.updateAppealById(id))
            return Result.success();
        else
            return Result.error("数据更新失败！");
    }

    @DeleteMapping
    public Result deleteAppeal(@RequestParam List<Integer> ids) {
        if (appealService.deleteAppeals(ids))
            return Result.success();
        else
            return Result.error("数据删除失败！");
    }
    @GetMapping("/status/{status}")
    public Result findAllByStatus(@PathVariable Integer status){
        return Result.success(appealService.findAllByStatus(status));
    }
}
