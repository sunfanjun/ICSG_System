package com.zjut.springbootprj.service.impl;

import com.zjut.springbootprj.common.Dangers;
import com.zjut.springbootprj.dao.DangerMapper;
import com.zjut.springbootprj.dao.ImageMapper;
import com.zjut.springbootprj.pojo.Danger;
import com.zjut.springbootprj.pojo.Image;
import com.zjut.springbootprj.pojo.Monitor;
import com.zjut.springbootprj.pojo.dto.DangerDto;
import com.zjut.springbootprj.service.DetectService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

@Service
public class DetectServiceImpl implements DetectService {
    @Resource
    DangerMapper dangerMapper;
    @Resource
    ImageMapper imageMapper;
    @Override
    public Boolean detectDanger(String url, Danger danger) {
        dangerMapper.save(danger);
        int id=dangerMapper.queryId(danger);
        System.out.println(id);
        danger.setId(id);
        Image image=new Image();
        image.setLink(url);
        image.setDanger(danger);
        imageMapper.save(image);
        return null;
    }

    @Override
    public Danger isDangerous(Set<String> set) {
        // 获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();
        // 定义目标日期时间格式
        DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化为字符串
        String formattedTime = currentTime.format(targetFormatter);
        System.out.println("Formatted Time: " + formattedTime);
        // 将字符串解析为 LocalDateTime
        LocalDateTime parsedTime = LocalDateTime.parse(formattedTime, targetFormatter);
        // 获取枚举数组
        Dangers[] behaviors = Dangers.values();
        System.out.println(behaviors);
        for(String s:set ){
            for (Dangers dangers:behaviors){
                if(dangers.name().toLowerCase().equals(s)){
                    Danger danger=new Danger();
                    Monitor monitor=new Monitor();
                    monitor.setId(1);
                    danger.setType(dangers.name());
                    danger.setMonitor(monitor);
                    danger.setDispose(0);
                    danger.setFTime(parsedTime);
                    return danger;
                }
            }
        }
        return null;
    }
}
