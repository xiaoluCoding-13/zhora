package com.zhora.admin.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;

/**
 * @author zhehen.lu
 * @date 2025/7/27 15:06
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("zhora-admin文档")
                .version("1.0.0")
                .description("zhora项目接口文档")
                .contact(new io.swagger.v3.oas.models.info.Contact()
                        .name("小鹿")
                        .email("luzhehen@163.com"))
                .license(new io.swagger.v3.oas.models.info.License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}