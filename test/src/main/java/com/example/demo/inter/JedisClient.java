package com.example.demo.inter;

import java.util.Set;

/**
 * @author stone.zhuang
 * @date 2018/4/3
 */
public interface JedisClient {
    String get(String key) throws Exception;

    String set(String key, String value) throws Exception;
}
