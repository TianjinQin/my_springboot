package com.test.myspringboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.io.IOException;
import java.sql.SQLException;

@Configuration
//@ComponentScan(basePackages = "com.myspringboot.dao.**")
@PropertySource(value = "classpath:mysql.properties")
public class DaoConfiguration {
    @Value("${jdbc.mysql.driver}")
    private String driverClassName;

    @Value("${mysql.username}")
    private String dbUserName;

    @Value("${mysql.password}")
    private String dbPassword;

    @Value("${mysql.url}")
    private String connUrl;

    @Value("${mysql.initialSize}")
    private Integer poolInitialSize;

    @Value("${mysql.minIdle}")
    private Integer poolMinIdle;

    @Value("${mysql.maxActive}")
    private Integer poolMaxActive;

    @Value("${mysql.maxWait}")
    private Long poolMaxWait;

    @Bean(name = "dzDataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource dzDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(dbUserName);
        druidDataSource.setPassword(dbPassword);
        druidDataSource.setUrl(connUrl);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setInitialSize(poolInitialSize);
        druidDataSource.setMinIdle(poolMinIdle);
        druidDataSource.setMaxActive(poolMaxActive);
        druidDataSource.setMaxWait(poolMaxWait);
        druidDataSource.setFilters("stat");
        return druidDataSource;
    }

    @Bean(name = "dzSessionFactory")
    public SqlSessionFactoryBean sessionFactory(@Qualifier("dzDataSource") DruidDataSource druidDataSource) throws IOException {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(druidDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/myspringboot/dao/mysql/**/*.xml"));
        return sessionFactory;
    }

    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("dzSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.myspringboot.dao.mysql");
        return mapperScannerConfigurer;
    }

    @Bean(name = "dzDataSourceTransactionManager")
    public DataSourceTransactionManager dzDataSourceTransactionManager(@Qualifier("dzDataSource") DruidDataSource druidDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(druidDataSource);
        return dataSourceTransactionManager;
    }

}
