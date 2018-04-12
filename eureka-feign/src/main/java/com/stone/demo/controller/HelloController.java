package com.stone.demo.controller;

import com.stone.demo.remote.HelloRemote;
import com.stone.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stone.zhuang
 * @date 2018/4/4
 */

@RestController
public class HelloController {

    @Autowired
    private HelloRemote helloRemote;

    @RequestMapping("/hello/{name}")
    public String hi(@PathVariable("name") String name){
        return helloRemote.hello(name);
    }

}
