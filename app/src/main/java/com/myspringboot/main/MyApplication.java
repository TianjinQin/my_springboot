package com.myspringboot.main;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.myspringboot.config")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, MultipartAutoConfiguration.class})
@EnableScheduling
public class MyApplication extends SpringBootServletInitializer{


    public static void main(String[] args) {
        SpringApplication application =new SpringApplication(MyApplication.class);

        application.setBannerMode(Banner.Mode.OFF);//关闭banner

        application.run(args);



   //     SpringApplication.run(MyApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MyApplication.class);
    }

}
