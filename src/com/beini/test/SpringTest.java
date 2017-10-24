package com.beini.test;

import com.beini.util.BLog;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

/**
 * Created by beini on 2017/10/20.
 */
public class SpringTest {
    private static ApplicationContext applicationContext;

    static {
        applicationContext = new ClassPathXmlApplicationContext("classpath*:config/spring-mvc.xml");
    }

    @Test
    public void testRedisConnection() {
        BLog.d("    testRedisConnection      ");
        RedisTemplate redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
//        redisTemplate.renameIfAbsent("abc", "bbb");//如果key=abc存在，则将key修改为bbb
//        System.out.println(redisTemplate);


    }
}
