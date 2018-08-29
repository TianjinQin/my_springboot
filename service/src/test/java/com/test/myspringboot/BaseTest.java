package com.test.myspringboot;

import com.test.myspringboot.config.DaoConfiguration;
import com.test.myspringboot.config.ServiceConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes ={DaoConfiguration.class, ServiceConfiguration.class})
@SpringBootTest
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@Transactional
@ComponentScan(basePackages = "com.test.myspringboot.config")
public class BaseTest {
}
