package com.example.demo.util;

import com.example.demo.comcon.JedisClientSharded;
import com.example.demo.config.RedisConfig;
import com.example.demo.inter.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

import javax.annotation.PostConstruct;

/**
 * @author stone.zhuang
 * @date 2018/4/3
 */

@Component
@Import(RedisConfig.class)
public class RedisUtil {

    @Autowired
    private ShardedJedisPool shardedJedisPool;
    private static JedisClient jedisClient;
    @PostConstruct
    public void init() {
        if ( shardedJedisPool!=null){
            jedisClient = new JedisClientSharded(shardedJedisPool);
        } else  {
            System.out.println("redis启动出错！");
        }
    }

    public static String get(String key) throws Exception {
        return jedisClient.get(key);
    }

    public static String set(String key, String value) throws Exception {
        return jedisClient.set(key,value);
    }

}
