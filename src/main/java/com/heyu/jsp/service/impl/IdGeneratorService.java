package com.heyu.jsp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author heyu
 */
@Service
public class IdGeneratorService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String ID_KEY = "id:generator:product";

    public Long incrementId() {
        return stringRedisTemplate.opsForValue().increment(ID_KEY);
    }
}
