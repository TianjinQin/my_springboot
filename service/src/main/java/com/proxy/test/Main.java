package com.proxy.test;

import com.sun.deploy.net.proxy.ProxyUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main {

    public static void main(String[] args) {

        IFly fly = new Bird();
        JdkProxy jdkProxy = new JdkProxy(fly);
          fly= (IFly) jdkProxy.createProxy();
        fly.fly();

        IMobile mobile = new Iphone();
        JdkProxy jdkProxyMobile = new JdkProxy(mobile);
        mobile = (IMobile) jdkProxyMobile.createProxy();
        mobile.call();
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
    }

}
