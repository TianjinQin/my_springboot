package com.test.myspringboot;

import com.myspringboot.service.OrderService;
import com.test.myspringboot.config.DaoConfiguration;
import com.test.myspringboot.config.ServiceConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes ={DaoConfiguration.class, ServiceConfiguration.class})
@ComponentScan(basePackages = "com.test.myspringboot.config")
@EnableAutoConfiguration//(exclude = {DataSourceAutoConfiguration.class, MultipartAutoConfiguration.class})
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;
    @Test
    public void buy() throws Exception {
        orderService.buy("apple", 3);
       /* CountDownLatch latch = new CountDownLatch(1);
       // for (int i = 0; i < 500; i++) {
            new Thread(new Buy(latch, 1, "apple")).start();
            latch.countDown();
       // }*/

    }

    class Buy implements Runnable {

        private CountDownLatch latch;
        private int buy;
        private String code;

        Buy(CountDownLatch latch, int buy, String code) {
            this.latch = latch;
            this.buy = buy;
            this.code = code;
        }

        @Override
        public void run() {
            try {
               // latch.await();
                orderService.buy(code, buy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}