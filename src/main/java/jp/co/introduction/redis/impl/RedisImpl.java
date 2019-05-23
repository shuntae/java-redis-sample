package jp.co.introduction.redis.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import jp.co.introduction.redis.client.RedisClient;
import jp.co.introduction.redis.constants.CacheConst;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.NamingStyle;

public class RedisImpl<T> {
    private Class<T> type;
    private RedisClient client;
    private String cacheKey;
    private int expire;

    public RedisImpl(Class<T> type) {
        this.type = type;
    }

    public void setUp(String cacheName, Object... param) {

        this.client = new RedisClient();

        List<String> keyList = new ArrayList<String>();
        keyList.add(cacheName);
        if (param != null) {
            Arrays.stream(param).forEach(p -> {
                if (p == null) {
                    keyList.add("nil");
                } else {
                    if (p instanceof List) {
                        List<?> workList = (List<?>) p;
                        List<String> mergeList = workList.stream().map(pp -> pp.toString())
                                .collect(Collectors.toList());
                        keyList.add(String.join("-", mergeList));
                    } else {
                        keyList.add(p.toString());
                    }
                }
            });
        }
        this.cacheKey = String.join(":", keyList);
        this.expire = 500;
    }

    public T get() {
        String val = client.get(this.cacheKey);
        if (val.equals(CacheConst.EMPTY)) {
            return null;
        } else {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            try {
                return (T) mapper.readValue(val, type);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public boolean set(Object val) {
        JSON json = new JSON();
        json.setPropertyStyle(NamingStyle.LOWER_UNDERSCORE);
        String getJson = json.format(val);
        return client.set(this.cacheKey, getJson, this.expire);
    }
}