package com.cn.demo.util.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: charge-api
 * @description: Redis服务工具类
 * @author: DongLianPo
 * @create: 2018-11-26 14:33
 **/
@Service
public class RedisServiceImpl {
    @Autowired
    /**redisTemplate进行操作数据的时候是Object类型的数据化操作*/
    private RedisTemplate redisTemplate;
    @Autowired
    /**stringRedisTemplate进行操作数据是String类型序列化操作*/
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    /**自定义模板,反序列化为自定义对象*/
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    /**
     * redis取数据
     *
     * @param key
     * @return
     */
    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * redis增加数据
     *
     * @param key
     * @param value
     */
    public void putValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * redis增加数据，设置过期时间，单位秒
     *
     * @param key
     * @param value
     * @param time
     */
    public void putValue(String key, String value, long time) {
        stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 删除数据
     *
     * @param key
     */
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 判断该数据是否存在
     *
     * @param key
     * @return boolean
     */
    public boolean isExisted(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * String Set
     *
     * @param key
     * @param value
     */
    public void strSet(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * String Get
     *
     * @param key
     * @return
     */
    public String strGet(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void strRemove(String key) {
        stringRedisTemplate.delete(key);
    }


    /**
     * redis 设置key生存时间
     *
     * @param key
     * @param value
     * @param timeout 时间（秒）
     * @param unit    秒的工具类
     */
    public void setKeyLiveTime(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }


    /**
     * 返回存储在键中的列表的指定元素。偏移开始和停止是基于零的索引，其中0是列表的第一个元素（列表的头部），1是下一个元素,end值:-1全部
     *
     * @param key
     * @param start
     * @param end
     * @return
     */

    public List<String> getRangeList(String key, long start, long end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }


    /**
     * 数据存放redis列表中，如果存在则存取，不存在key对应的列表则进行创建后存放(此方法是在列表值右侧存放)
     *
     * @param key
     * @param value
     */
    public void setList(String key, String value) {
        stringRedisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 获取list列表的长度
     *
     * @param key
     * @return
     */
    public Long getListSize(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    /**
     * 根据下标获取列表值
     *
     * @param key
     * @param index
     * @return
     */
    public String getListindex(String key, long index) {
        return stringRedisTemplate.opsForList().index(key, index);
    }

    /**
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件。
     * (count> 0：删除等于从头到尾移动的值的元素,
     * count <0：删除等于从尾到头移动的值的元素,
     * count = 0：删除等于value的所有元素。)
     *
     * @param key
     * @param count
     * @param value
     */
    public Long removeList(String key, long count, Object value) {
        Long aLong = stringRedisTemplate.opsForList().remove(key, count, value);
        return aLong;
    }

    /**
     * @param key
     * @param value
     * @description 存取数据，数据类型为对象
     */
    public void putRedisCacheValue(String key, Object value) {
        redisCacheTemplate.opsForValue().set(key, (Serializable) value);

    }

    /**
     * @param key
     * @return
     * @description 获取数据
     */
    public Object getRedisCacheValue(String key) {
        return redisCacheTemplate.opsForValue().get(key);
    }


}
