package com.zhora.ai.config.ai;

import com.zhora.dto.ai.AiLlmConfigDTO;
import com.zhora.dto.ai.AiLlmConfigSearchDTO;
import com.zhora.enums.ai.LlmCodeEnum;
import com.zhora.ai.config.ai.model.QwenEmbeddingModelConfig;
import com.zhora.ai.config.ai.properties.JdbcInfo;
import com.zhora.ai.config.ai.properties.PgVectorProperties;
import com.zhora.service.ai.IAiLlmConfigService;
import dev.langchain4j.community.model.dashscope.QwenEmbeddingModel;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
@RequiredArgsConstructor
public class EmbeddingInitializer {

    @Resource
    private PgVectorProperties pgVectorProperties;

    private final IAiLlmConfigService aiLlmConfigService;

    @Bean
    public QwenEmbeddingModelConfig qwenEmbeddingModelConfig() {
        QwenEmbeddingModelConfig qwenEmbeddingModelConfig = new QwenEmbeddingModelConfig();

        AiLlmConfigSearchDTO searchDTO = new AiLlmConfigSearchDTO();
        searchDTO.setCode(LlmCodeEnum.Q_WEN_EMBEDDING);
        AiLlmConfigDTO deepSeek = aiLlmConfigService.getDetail(searchDTO);
        qwenEmbeddingModelConfig.init(deepSeek);
        return qwenEmbeddingModelConfig;
    }

    @Bean
    public EmbeddingModel qwenEmbeddingModel(QwenEmbeddingModelConfig qwenEmbeddingModelConfig) {
        return QwenEmbeddingModel.builder()
                .apiKey(qwenEmbeddingModelConfig.getApiKey())
                .modelName(qwenEmbeddingModelConfig.getModelName())
                .dimension(1024)
                .build();
    }

    @Bean(name = "qwenEmbeddingStore")
    public EmbeddingStore<TextSegment> qwenEmbeddingStore() {
        JdbcInfo jdbcInfo = parseJdbcUrl(pgVectorProperties.getUrl());
        return PgVectorEmbeddingStore.builder()
                .host(jdbcInfo.getHost())
                .port(jdbcInfo.getPort())
                .database(jdbcInfo.getDatabase())
                .user(pgVectorProperties.getUsername())
                .password(pgVectorProperties.getPassword())
                .table(jdbcInfo.getDatabase() + ".qwen_embedding_store")
                .dimension(2048)
                .build();
    }

//    @Bean(name = "qwenPuLibraryEmbeddingStore")
//    public EmbeddingStore<TextSegment> qwenPuLibraryEmbeddingStore() {
//        JdbcInfo jdbcInfo = parseJdbcUrl(pgVectorProperties.getUrl());
//        return PgVectorEmbeddingStore.builder()
//                .host(jdbcInfo.getHost())
//                .port(jdbcInfo.getPort())
//                .database(jdbcInfo.getDatabase())
//                .user(pgVectorProperties.getUsername())
//                .password(pgVectorProperties.getPassword())
//                .table(jdbcInfo.getDatabase() + ".qwen_library_embedding_store")
//                .dimension(2048)
//                .build();
//    }

    public JdbcInfo parseJdbcUrl(String jdbcUrl) {
        // 正则表达式匹配JDBC URL
        Pattern pattern = Pattern.compile(
                "jdbc:postgresql://([^:/]+)(?::(\\d+))?/([^?]+)(?:\\?.*)?");
        Matcher matcher = pattern.matcher(jdbcUrl);

        if (matcher.find()) {
            String host = matcher.group(1);
            // 如果没有端口号，使用PostgreSQL默认端口5432
            String portStr = matcher.group(2);
            int port = portStr != null ? Integer.parseInt(portStr) : 5432;
            String database = matcher.group(3);
            JdbcInfo jdbcInfo = new JdbcInfo();
            jdbcInfo.setHost(host);
            jdbcInfo.setPort(port);
            jdbcInfo.setDatabase(database);
            return jdbcInfo;
        } else {
            throw new IllegalArgumentException("Invalid JDBC URL format");
        }
    }
}
