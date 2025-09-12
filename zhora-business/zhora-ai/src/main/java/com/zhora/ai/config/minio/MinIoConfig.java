package com.zhora.ai.config.minio;

import io.minio.MinioClient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
@Getter
@ConfigurationProperties(prefix = "minio")
@Slf4j
public class MinIoConfig {
  private String endpoint;
  private String accessKey;
  private String secretKey;
  private String defaultBucket;

  @Bean
  public MinioClient minioClient() {
    return MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
  }
}
