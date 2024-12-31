package com.zjut.springbootprj.demos.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Demo2 {
    public static void main(String[] args) {
        // 谷歌驱动
        ChromeOptions options = new ChromeOptions();
        // 允许所有请求
        options.addArguments("--remote-allow-origins=*");
        WebDriver webDriver = new ChromeDriver(options);
        // 启动需要打开的网页
        webDriver.get("https://www.baidu.com");
    }
}
