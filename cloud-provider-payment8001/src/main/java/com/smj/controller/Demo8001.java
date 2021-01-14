package com.smj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo8001 {
    @GetMapping("/demo")
    public String getTest(){
        return "测试成功";
    }
}
