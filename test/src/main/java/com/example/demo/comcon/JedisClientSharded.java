package com.example.demo.comcon;

import com.example.demo.inter.JedisClient;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Set;

/**
 * @author stone.zhuang
 * @date 2018/4/3
 */
public class JedisClientSharded implements JedisClient {

   private static ShardedJedisPool pool;

    public JedisClientSharded (ShardedJedisPool shardedJedisPool){
        pool = shardedJedisPool;
    }

    @Override
    public String get(String key) throws Exception {
        ShardedJedis jedis = pool.getResource();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            throw e;
        } finally {
            //pool.close();
            pool.returnBrokenResource(jedis);
        }
    }

    @Override
    public String set(String key, String value) throws Exception {
        ShardedJedis jedis = pool.getResource();
        try {
            return jedis.set(key,value);
        } catch (Exception e) {
            throw e;
        } finally {
            pool.returnBrokenResource(jedis);
        }
    }

}
