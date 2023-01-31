package com.ylb.shardingDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：楼兰
 * @date ：Created in 2021/1/4
 * @description:
 **/

@MapperScan("com.ylb.shardingDemo.mapper")
@SpringBootApplication
public class ShardingJDBCApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingJDBCApplication.class,args);
    }
}
