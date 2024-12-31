package com.zjut.springbootprj.controller;

import com.zjut.springbootprj.common.Result;
import com.zjut.springbootprj.pojo.Image;
import com.zjut.springbootprj.service.ImageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Resource
    private ImageService imageService;
    @PostMapping
    public Result saveImage(@RequestBody Image image){
        if(imageService.saveImage(image))
            return Result.success();
        else
            return Result.error("数据插入失败！");
    }
    @PutMapping
    public Result updateImage(@RequestBody Image image){
        if(imageService.updateImage(image))
            return Result.success();
        else
            return Result.error("数据更新失败！");
    }
    @DeleteMapping
    public Result deleteImage(@RequestParam List<Integer> ids){
        if(imageService.deleteImages(ids))
            return Result.success();
        else
            return Result.error("数据删除失败！");
    }
}
