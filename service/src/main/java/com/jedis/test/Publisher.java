package com.jedis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Publisher {
    private JedisPool jedisPool;
    private String channelName;

    public Publisher(JedisPool jedisPool, String channelName) {
        this.jedisPool = jedisPool;
        this.channelName = channelName;
    }

    public void publishMsg(String msg) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.publish(channelName, msg);

        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxIdle(10);
        config.setMaxWaitMillis(20000);
        JedisPool jedisPool = new JedisPool(config, "10.28.6.14", 6379, 20000, "yzmannzai");

  /*      String channelName = "my_channel";
        Publisher publisher=new Publisher(jedisPool,channelName);
        publisher.publishMsg("ok");
        publisher.publishMsg("byebye");*/
jedisPool.getResource().set("score","6");
        jedisPool.getResource().del("score");

    }


}
