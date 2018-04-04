package com.stone.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author stone.zhuang
 * @date 2018/4/4
 */

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    public String hiService (String name){
        return restTemplate.getForObject("http://EUREKA-CLIENT/hi?name="+name,String.class);
    }
}
