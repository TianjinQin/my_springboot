package com.jedis.test;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyLinkedHashMap extends LinkedHashMap {

    private static final int INITAL_CAPACITY = 16;

    public MyLinkedHashMap() {
        this(true);
    }

    public MyLinkedHashMap(boolean accessOrder) {
        super(INITAL_CAPACITY, 0.75f, accessOrder);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {

        return size() > INITAL_CAPACITY;
    }

    public static void main(String[] args) {
        MyLinkedHashMap myLinkedHashMap = new MyLinkedHashMap();
    }
}
