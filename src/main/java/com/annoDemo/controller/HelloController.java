package com.annoDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  控制器类
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    public String sayHello(){
        System.out.println("请求响应");
        return "success";
    }
}
