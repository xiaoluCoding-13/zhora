package com.zhora.ai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@SpringBootApplication(scanBasePackages = {"com.zhora.service", "com.zhora.ai","com.zhora.common.config"})
@MapperScan(value = {"com.zhora.mapper"})
public class ZhoraAiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ZhoraAiApplication.class, args);
        // 打印所有消息转换器
        RequestMappingHandlerAdapter adapter = ctx.getBean(RequestMappingHandlerAdapter.class);
        adapter.getMessageConverters().forEach(converter -> {
            System.out.println("消息转换器: " + converter.getClass().getName());
        });
    }

}
