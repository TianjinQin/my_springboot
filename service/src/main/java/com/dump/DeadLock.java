package com.dump;

import java.io.IOException;

public class DeadLock {

    public static void main(String[] args) throws IOException {
       // dg();
     /*   while (true) {
            System.in.read();
        }*/
        dead();
    }

    public static void dg() {
        System.out.println("byd");
        dg();
    }


    public static void dead() {
        final Object obj_1 = new Object(), obj_2 = new Object();

        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                synchronized (obj_1) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                    }

                    synchronized (obj_2) {
                        System.out.println("thread t1 done.");
                    }
                }
            }
        };

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                synchronized (obj_2) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                    }

                    synchronized (obj_1) {
                        System.out.println("thread t2 done.");
                    }
                }
            }
        };

        t1.start();
        t2.start();
    }
}