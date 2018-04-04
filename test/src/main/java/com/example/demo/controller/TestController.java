package com.example.demo.controller;

import com.example.demo.util.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stone.zhuang
 * @date 2018/4/3
 */
@RestController
public class TestController {


    @RequestMapping("/first")
    public String firstRedis() throws Exception {
        RedisUtil.set("a","11111111111111111111111111111111111");
        return  RedisUtil.get("a");
    }

}
