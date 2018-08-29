package com.myspringboot.service;

public interface MemoryStorageService {

    int setNx(String key, String value, int et);
   int delete(String key, String value);


}
