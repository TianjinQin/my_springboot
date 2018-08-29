package com.myspringboot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages ="com.myspringboot.core.**")
public class CoreConfiguration {
}
