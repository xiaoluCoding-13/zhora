//package com.zhora.admin.config.ai;
//
//import com.zhora.admin.config.ai.model.QwenEmbeddingModelConfig;
//import com.zhora.dto.ai.AiConfigDTO;
//import com.zhora.dto.ai.AiConfigSearchDTO;
//import com.zhora.enums.ai.CodeEnum;
//import com.zhora.service.ai.IAiConfigService;
//import dev.langchain4j.community.model.dashscope.QwenEmbeddingModel;
//import dev.langchain4j.data.segment.TextSegment;
//import dev.langchain4j.model.embedding.EmbeddingModel;
//import dev.langchain4j.store.embedding.EmbeddingStore;
//import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
//import jakarta.annotation.Resource;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//@Configuration
//@RequiredArgsConstructor
//public class EmbeddingInitializer {
//
//    @Resource
//    private Environment env;
//    private final IAiConfigService aiConfigService;
//
//    @Bean
//    public QwenEmbeddingModelConfig qwenEmbeddingModelConfig() {
//        QwenEmbeddingModelConfig qwenEmbeddingModelConfig = new QwenEmbeddingModelConfig();
//
//        AiConfigSearchDTO searchDTO = new AiConfigSearchDTO();
//        searchDTO.setCode(CodeEnum.Q_WEN_EMBEDDING);
//        AiConfigDTO deepSeek = aiConfigService.getDetail(searchDTO);
//        qwenEmbeddingModelConfig.init(deepSeek);
//        return qwenEmbeddingModelConfig;
//    }
//
//    @Bean
//    public EmbeddingModel qwenEmbeddingModel(QwenEmbeddingModelConfig qwenEmbeddingModelConfig) {
//        return QwenEmbeddingModel.builder()
//                .apiKey(qwenEmbeddingModelConfig.getApiKey())
//                .modelName(qwenEmbeddingModelConfig.getModelName())
//                .dimension(2048)
//                .build();
//    }
//
//    @Bean
//    public EmbeddingStore<TextSegment> qwenEmbeddingStore() {
////    String hostPort = env.getProperty("DATABASE_HOST_PORT");
////    String host = hostPort.split(":")[0];
//        return PgVectorEmbeddingStore.builder()
//        return ve.builder()
//                .host("127.0.0.1")
//                .port(5432)
//                .database("mjga")
//                .user("mjga")
//                .password("mjga")
//                .table("mjga.zhipu_embedding_store")
//                .dimension(2048)
//                .build();
//    }
//
//    @Bean
//    public EmbeddingStore<TextSegment> qwenLibraryEmbeddingStore() {
////    String hostPort = env.getProperty("DATABASE_HOST_PORT");
////    String host = hostPort.split(":")[0];
//        return PgVectorEmbeddingStore.builder()
//                .host("127.0.0.1")
//                .port(5432)
//                .database("mjga")
//                .user("mjga")
//                .password("mjga")
//                .table("mjga.zhipu_library_embedding_store")
//                .dimension(2048)
//                .build();
//    }
//}
