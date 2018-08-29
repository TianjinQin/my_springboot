package com.jedis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class SubscriberThread implements Runnable {
    private JedisPool jedisPool;
    private Subscriber subscriber;
    private String channelName;

    public SubscriberThread(JedisPool jedisPool, Subscriber subscriber, String channelName) {
        this.jedisPool = jedisPool;
        this.subscriber = subscriber;
        this.channelName = channelName;
    }

    @Override
    public void run() {

            jedisPool.getResource().subscribe(subscriber, channelName);
    }


    public static void main(String[] args) throws InterruptedException {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxIdle(10);
        config.setMaxWaitMillis(20000);
        JedisPool jedisPool = new JedisPool(config, "10.28.6.14", 6379, 20000, "yzmannzai");

       /* String channelName = "my_channel";
        Subscriber subscriber=new Subscriber();
        SubscriberThread thread = new SubscriberThread(jedisPool, subscriber, channelName);
        new Thread(thread).start();
        Thread.sleep(1000*10);
        subscriber.unsubscribe(channelName);*/
     Jedis jedis= jedisPool.getResource();
        jedis.hset("xcx:secret:czg","xcx-app-id","wxa84c8b24b926fbf7");
        jedis.hset("xcx:secret:czg","xcx-app-secret","7d653243f252cc95329e2c41c29abf30");

    }
}
