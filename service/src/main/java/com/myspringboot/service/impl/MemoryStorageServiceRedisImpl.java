package com.myspringboot.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jedis.test.Publisher;
import com.jedis.test.Subscriber;
import com.jedis.test.SubscriberThread;
import com.myspringboot.service.MemoryStorageService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;


public class MemoryStorageServiceRedisImpl implements MemoryStorageService {

    private static final int MAX_TOTAL = 128;
    private static final int MAX_IDLE = 128;
    private static final int MAX_WAIT_MILLS = 10000;
    private static final int TIME_OUT = 10000;
    private JedisPool jedisPool;
    private String host = "localhost";
    private int port = 6379;
    private String password;


    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void init() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_TOTAL);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT_MILLS);
        jedisPool = new JedisPool(config, host, port, TIME_OUT, password);
    }


    @Override
    public int setNx(String key, String value, int et) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis == null) return 0;
            String reply = jedis.set(key, value, "NX", "EX", et);
            if (StringUtils.isEmpty(reply)) {
                return 0;
            }
            return 1;
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }
    /*    return command(jedis -> {
            String reply = jedis.set(key, value, "NX", "EX", et);

            return StringUtils.isEmpty(reply) ? 0 : 1;
        });*/
    }


    private <R> R command(Function<Jedis, R> callbcak) {
        try (Jedis jedis = jedisPool.getResource()) {
            return callbcak.apply(jedis);
        } catch (Exception e) {
            throw e;
        }
    }

    public int delete(String key, String value) {
        String script = "if redis.call(\"get\", KEYS[1]) == ARGV[1]\n" +
                "then\n" +
                "    return redis.call(\"del\", KEYS[1])\n" +
                "else\n" +
                "    return 0\n" +
                "end";
        List<String> keys = Lists.newArrayList(key);
        List<String> values = Lists.newArrayList(value);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis == null) return 0;
            Integer reply = Integer.parseInt(jedisPool.getResource().eval(script, keys, values).toString());

            return reply;
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }
    }


    public static void main(String[] args) {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_TOTAL);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT_MILLS);
        JedisPool jedisPool = new JedisPool(config, "10.28.6.14", 6379, TIME_OUT, "yzmannzai");

        Jedis jedis = jedisPool.getResource();
      /*  String sortKey = "sort:u";
        for (int i = 0; i < 5; i++) {
            String key = "test:u:" + i;
            for (int j = 0; j < 5; j++) {
                jedis.sadd(key, j + "");
            }
            jedis.zadd("sort:u", jedis.scard(key), key);
        }
        String key = "test:u:" + 0;
*//*        String key1 = "test:u:" + 1;
        jedis.sadd(key, 8 + "");
        System.out.println(jedis.smembers(key));

        System.out.println(jedis.scard(key));*//*
        jedis.srem(key, 7 + "");
     *//*   System.out.println(jedis.smembers(key));

        jedis.zadd("sort:u", jedis.scard(key), key);
        jedis.zadd("sort:u", jedis.scard(key1), key1);*//*
        System.out.println(jedis.zrange(sortKey, 0, 2));

        String hmKey = "hmkey" + 0;
        Map<String, String> map = Maps.newHashMap();
        map.put("a", "a");
        map.put("b", "b");
        jedis.hmset(hmKey, map);

        System.out.println(jedis.hmget(hmKey, "a"));
        Transaction transaction = jedis.multi();

        transaction.exec();
        transaction.discard();
//      jedis.zincrby();
//    jedis.hincrBy()*/
        //   JedisPubSub pubSub=new
     /*   String channelName = "my_channel";


        Publisher publisher=new Publisher(jedisPool,channelName);
        publisher.publishMsg("ok");
        publisher.publishMsg("byebye");
        SubscriberThread thread = new SubscriberThread(jedisPool, new Subscriber(), channelName);
        new Thread(thread).start();*/
     /*   jedis.watch("score");

        Transaction re = jedis.multi();
        re.set("score", "32");
        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Object> result=re.exec();
        System.out.println(re.exec());

      Pipeline pipeline=  jedis.pipelined();
     List<Object> list= pipeline.syncAndReturnAll();*/
     for (int i=0;i<6;i++) {
         jedis.incr("test:score");
      System.out.println(jedis.get("test:score"));
     }
    }


}
