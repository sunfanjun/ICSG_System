package com.zjut.springbootprj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjut.springbootprj.common.Dangers;
import com.zjut.springbootprj.common.Result;
import com.zjut.springbootprj.pojo.Danger;
import com.zjut.springbootprj.service.DetectService;
import com.zjut.springbootprj.utils.PostUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/detect")
public class DetectController {
    @Resource
    DetectService detectService;

    @PostMapping("/{model}")
    public Result detect(@PathVariable String model ,@RequestParam("file") MultipartFile file){
        String path="D:\\test";
        String filename=file.getOriginalFilename();
        File file1= new File(path+"\\"+filename);
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(filename);
        System.out.println(model);
        String link=file1.getAbsolutePath();
        System.out.println(link);
        String api="http://10.81.110.252:5000/detect/"+model;
        Set<String> set=new HashSet<>();

        String result= PostUtils.uploadStream(api,link);
        System.out.println(result);
        JSONObject jsonObject = JSON.parseObject(result);
        String type = jsonObject.getString("type");
        System.out.println(type);
        set.add(type);
        System.out.println(set);
//        List<Map<String,String >> list= JSON.parseObject(result, List.class);
//        for (Map<String,String> m:list
//        ) {
//            System.out.println("1234");
//            System.out.println(m);
//            set.add(m.get("name"));
//        }
//        for(String s:set){
//            System.out.println(s);
//        }
        Danger danger =detectService.isDangerous(set);
        if(danger!=null&&type.equals("摔倒")){
            danger.setType("摔倒");
            detectService.detectDanger(link,danger);
            System.out.println(danger.getType());
            return Result.success(danger.getType());
        }
        return Result.success();
    }
}
