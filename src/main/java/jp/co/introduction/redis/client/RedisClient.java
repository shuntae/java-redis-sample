package jp.co.introduction.redis.client;

import jp.co.introduction.redis.constants.CacheConst;
import redis.clients.jedis.Jedis;

public class RedisClient {

    private Jedis client;

    public RedisClient() {
        getConnection();
    }

    private void getConnection() {
        this.client = new Jedis("127.0.0.1", 6379);
    }

    public String get(String key) {
        System.out.println("===== [START] ===== : #get");
        System.out.println("### key = [" + key + "]");
        String val = client.get(key);
        try {
            if (val == null) {
                System.out.println("### get result = [null]");
                return CacheConst.EMPTY;

            } else if (val.equals(CacheConst.EMPTY)) {
                System.out.println("### get result = [null]");
                return CacheConst.EMPTY;

            } else {
                System.out.println("### get result = [" + val + "]");
                return val;
            }
        } finally {
            System.out.println("===== [E N D] ===== : #get");
        }
    }

    public boolean set(String key, String val, int expire) {
        System.out.println("===== [START] ===== : #set");
        System.out.println("### cache info : key = [" + key + "], value = [" + val + "]");

        String setResult = client.set(key, val);
        try {
            if (setResult.equals("OK")) {
                System.out.println("### set success!");
                return client.expire(key, expire) == 1;
            } else {
                System.out.println("### set fail");
                return false;
            }
        } finally {
            System.out.println("===== [E N D] ===== : #set");
        }
    }
}