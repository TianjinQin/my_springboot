package com.myspringboot.service;

public interface OrderService {


  void   buy(String code,int buy) throws Exception;
  int getCount();

}
