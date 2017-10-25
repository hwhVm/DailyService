package com.beini.util;

import com.beini.bean.TokenBean;
import com.beini.constant.NetConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * Created by beini on 2017/10/23.
 */
@Component
public class RedisCacheUtil {

    @Autowired
    private RedisTemplate stringRedisTemplate;


    public RedisCacheUtil() {
        BLog.d(" RedisCacheUtil ");
//        stringRedisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    public TokenBean createToken(int userId) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenBean model = new TokenBean(userId, token);
        //存储到redis并设置过期时间

        stringRedisTemplate.boundValueOps(String.valueOf(userId)).set(token, NetConstants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    public TokenBean getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        int userId = Integer.parseInt(param[0]);
        String token = param[1];
        return new TokenBean(userId, token);
    }

    public boolean checkToken(TokenBean model) {
        if (model == null) {
            return false;
        }
        String token = (String) stringRedisTemplate.boundValueOps(model.getUserId()).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        stringRedisTemplate.boundValueOps(model.getUserId()).expire(NetConstants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    public void deleteToken(long userId) {
        stringRedisTemplate.delete(userId);
    }


    //-----------------------------------

    /**
     * 向Hash中添加值
     *
     * @param key   可以对应数据库中的表名
     * @param field 可以对应数据库表中的唯一索引
     * @param value 存入redis中的值
     */
    public void setValue(String key, String field, String value) {
        if (!StringUtils.isEmpty(key)) {
            stringRedisTemplate.opsForHash().put(key, field, value);
        } else {
            throw new NullPointerException("key no for null");
        }
    }

    /**
     * @param key
     * @param field
     */
    public String getValue(String key, String field) {

        return (String) stringRedisTemplate.opsForHash().get(field, key);
    }

    /**
     * 判断 是否存在 key 以及 hash key
     *
     * @param key
     * @param field
     * @return
     */
    public boolean isExistKey(String key, String field) {
        if (!StringUtils.isEmpty(key)) {
            return false;
        }
        return stringRedisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 查询 key中对应多少条数据
     *
     * @param key
     * @return
     */
    public long returnSize(String key) {
        if (StringUtils.isEmpty(key)) {
            return 0L;
        }
        return stringRedisTemplate.opsForHash().size(key);
    }

    /**
     * 删除
     *
     * @param key
     * @param field
     */
    public void deteleKey(String key, String field) {
        stringRedisTemplate.opsForHash().delete(key, field);
    }
}
