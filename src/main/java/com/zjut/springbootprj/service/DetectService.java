package com.zjut.springbootprj.service;

import com.zjut.springbootprj.pojo.Danger;

import java.util.Set;

public interface DetectService {

    Boolean detectDanger(String url , Danger danger);



    Danger isDangerous(Set<String> set);
}
