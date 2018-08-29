package com.myspringboot.controller.order;

import com.google.common.collect.Maps;
import com.myspringboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/test")
    @ResponseBody
    public void test2() throws Exception {


        int j = 0;
        while(true){
            System.out.println(j++);
            ExecutorService executors = Executors.newFixedThreadPool(50);
            int i=0;
            while(i++<10){
                executors.submit(new Runnable() {
                    public void run() {
                    }
                });
            }
        }


    }

    public static void dg(List<String> strings, Integer i) {
        strings.add(new String((i++) + ""));
        dg(strings, i);
    }

    public Map<String, Integer> test() throws Exception {

        Map<String, Integer> map = Maps.newHashMap();

        final CountDownLatch latch = new CountDownLatch(20000);

        for (int i = 0; i < 20000; i++) {
            System.out.println(i);
            new Thread(new Buy(latch, 1, "apple")).start();
            latch.countDown();
        }
        Thread.sleep(5000);
        System.out.println(orderService.getCount());
        map.put("count", orderService.getCount());
        return map;
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
                latch.await();
                orderService.buy(code, buy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
