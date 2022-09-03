package com.myspringboot.config;

import com.myspringboot.vo.ResultVo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

//    @Bean(name = "notebookPC")
//    public ResultVo computer1(){
//        System.out.println("初始化notebookPC");
//        return new ResultVo();
//    }
//   // @ConditionalOnMissingBean(ResultVo.class)
//   // @ConditionalOnBean(ResultVo.class)
//    @Bean("reservePC")
//    public ResultVo computer2(){
//        System.out.println("初始化reservePC");
//        return new ResultVo();
//    }

}
