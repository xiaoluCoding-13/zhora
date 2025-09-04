package com.zhora.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.zhora.service", "com.zhora.admin","com.zhora.common.config"})
@MapperScan(value = {"com.zhora.mapper"})
public class ZhoraAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhoraAdminApplication.class, args);
    }

}
