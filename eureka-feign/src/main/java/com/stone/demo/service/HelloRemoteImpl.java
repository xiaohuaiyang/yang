package com.stone.demo.service;

import com.stone.demo.remote.HelloRemote;
import org.springframework.stereotype.Component;

/**
 * @author stone.zhuang
 * @date 2018/4/10
 */
@Component
public class HelloRemoteImpl implements HelloRemote {
    @Override
    public String hello(String name) {
        return "sorry," + name +",this app is error!";
    }
}
