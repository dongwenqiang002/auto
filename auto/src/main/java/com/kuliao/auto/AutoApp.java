package com.kuliao.auto;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackageClasses = com.kuliao.auto.db.mapper.PathMapper.class)
@EnableTransactionManagement
public class AutoApp {

    public static void main(String[] args) {
        SpringApplication.run(AutoApp.class,args);
    }

}
