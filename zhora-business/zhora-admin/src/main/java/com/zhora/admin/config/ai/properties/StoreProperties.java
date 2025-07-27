package com.zhora.admin.config.ai.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhehen.lu
 * @date 2025/7/27 15:40
 */
@Component
@ConfigurationProperties(prefix = "langchain4j.embeddingstore")
public class StoreProperties {
    private RedisProperties redis;

    public RedisProperties getRedis() {
        return redis;
    }

    public void setRedis(RedisProperties redis) {
        this.redis = redis;
    }


    public static class RedisProperties {
        private String host;

        private int port;

        private String password;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
