package main.com.store.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
    private static JedisPoolConfig config;
    private static JedisPool jedisPool;

    static {
        config = new JedisPoolConfig();
        config.setMaxIdle(20);
        config.setMinIdle(5);
        jedisPool = new JedisPool(config, "127.0.0.1", 6379);
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static void closeJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }

    }
}
