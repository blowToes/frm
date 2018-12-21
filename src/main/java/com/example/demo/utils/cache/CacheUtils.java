package com.example.demo.utils.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CacheUtils
 * @Description TO_DO  Guava实现缓存，定时刷新机制
 * @Author ZHAAIKAIXUAN
 * @Date 2018/12/21 14:20
 * @Version 1.0
 */
@Data
public abstract class CacheUtils<K, V> {

    private final static Logger LOGGER = LoggerFactory.getLogger(CacheUtils.class);

    private TimeUnit timeUnit = TimeUnit.DAYS;

    private int expireAfterWrite = 30;

    private Date resetTime;     //Cache初始化或被重置的时间

    public LoadingCache<K, V> getCache(){

        LoadingCache<K, V> cache = CacheBuilder.newBuilder()
               .expireAfterWrite(expireAfterWrite,timeUnit)
                .recordStats()
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K key) throws Exception {
                        // 缓存加载逻辑
                        return fetchData(key);
                    }
                });
        this.resetTime = new Date();

        return cache;
    }


    /**
     * 根据key从数据库或其他数据源获取数据
     */
    protected abstract V fetchData(K k);

}
