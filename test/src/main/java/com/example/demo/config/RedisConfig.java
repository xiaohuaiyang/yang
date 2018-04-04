package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stone.zhuang
 * @date 2018/4/3
 */
@Component
public class RedisConfig {

    @Value("${redis.host: 127.0.0.1:6379}")
    private String host;
    private int port;
    @Value("${redis.password:}")
    private String password;
    @Value("${redis.connectionTimeout: 3000}")
    private int connectionTimeout;
    @Value("${redis.soTimeout: 3000}")
    private int soTimeout;
    @Value("${redis.maxAttempts: 5}")
    private int maxAttempts;
    @Value("${redis.pool.minIdle: 5}")
    private int minIdle;
    @Value("${redis.pool.maxIdle: 20}")
    private int maxIdle;
    @Value("${redis.pool.maxWaitMillis: 3000}")
    private long maxWaitMillis;
    @Value("${redis.pool.maxTotal: 2000}")
    private int maxTotal;
    @Value("${redis.pool.testOnBorrow: true}")
    private boolean testOnBorrow;
    @Value("${redis.pool.testOnReturn: true}")
    private boolean testOnReturn;

    @Bean
    public ShardedJedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        //获取服务器数组
        String[] serverArray = host.split(",");

        List<JedisShardInfo> jdsInfoList = new ArrayList<JedisShardInfo>(serverArray.length);
        if("".equals(password) || password==null) {
            password=null;
        }

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            JedisShardInfo jedisShardInfo = new JedisShardInfo(ipPortPair[0].trim(),  Integer.parseInt(ipPortPair[1].trim()));
            jedisShardInfo.setPassword(password);
            jedisShardInfo.setConnectionTimeout(connectionTimeout);
            jedisShardInfo.setSoTimeout(soTimeout);
            jdsInfoList.add(jedisShardInfo);
        }
        ShardedJedisPool pool = new ShardedJedisPool(jedisPoolConfig, jdsInfoList);
        System.out.println("*** redis load success");
        System.out.println("*** instance size: "+jdsInfoList.size());
        System.out.println("*** host: "+host);
        System.out.println("*** redis load end");
        return  pool;
    }

}
