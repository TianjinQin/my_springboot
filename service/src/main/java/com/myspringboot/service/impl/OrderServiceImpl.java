package com.myspringboot.service.impl;

import com.myspringboot.dao.mysql.dao.OrderMapper;
import com.myspringboot.service.MemoryStorageService;
import com.myspringboot.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    @Qualifier("memoryStorageServiceRedisImpl")
    private MemoryStorageService memoryStorageServiceRedisImpl;
    private ThreadLocal<String> lockValue = new ThreadLocal<>();
    public static int count = 0;


    @Override
    public void buy(String code, int buy) throws Exception {

        String value = getRandomValue();
        lockValue.set(value);
        String key = "test:" + code;
        buy(key, code, value, buy);

    }

    @Override
    public int getCount() {
        return count;
    }


    public void buy(String key, String code, String value, int buy) throws Exception {
        int reply = memoryStorageServiceRedisImpl.setNx(key, value, 10);
        if (reply == 1) {
            int amount = orderMapper.getAmount(code);
            if (amount < buy) {
                memoryStorageServiceRedisImpl.delete(key, lockValue.get());
                return;
            }
            update(code, buy);
            System.out.println("count:" + count);
            int redisValue = memoryStorageServiceRedisImpl.delete(key, lockValue.get());
            lockValue.remove();
            if (redisValue <= 0) {
                throw new Exception("没抢到");
            }
        } else {
            int sleep = new Random().nextInt(10);
            Thread.sleep(sleep);
            buy(key, code, value, buy);
        }

    }

    @Transactional
    public void update(String code, int buy) throws Exception {
        int updateRaw = orderMapper.updateByBuy(code, buy);
        if (updateRaw <= 0) {
            throw new Exception("没抢到");
        } else {
            count++;
        }

    }

    private static String getRandomValue() {
        return UUID.randomUUID().toString();
    }

}
