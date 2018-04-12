package com.stone.demo.remote;

import com.stone.demo.service.HelloRemoteImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author stone.zhuang
 * @date 2018/4/8
 */

@FeignClient(name= "EUREKA-CLIENT",fallback = HelloRemoteImpl.class)
public interface HelloRemote {

    @RequestMapping(value="/hi")
    public String hello(@RequestParam(value = "name") String name);
}
