package com.zhora.admin.component;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author zhehen.lu
 * @date 2025/8/22 15:06
 */
@Data
@Component
public class PromptConfiguration {

    @jakarta.annotation.Resource private ResourceLoader resourceLoader;
    private String system;

    @PostConstruct
    public void init() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:prompt.txt");
        system = Files.readString(Paths.get(resource.getURI()));
    }
}