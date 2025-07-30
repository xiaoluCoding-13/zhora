package com.zhora.admin.config.ai;

import com.zhora.admin.component.PromptConfiguration;
import com.zhora.admin.config.ai.model.DeepSeekChatModelConfig;
import com.zhora.admin.config.ai.model.QwenChatModelConfig;
import com.zhora.admin.service.AiChatAssistant;
import com.zhora.dto.ai.AiLlmConfigDTO;
import com.zhora.dto.ai.AiLlmConfigSearchDTO;
import com.zhora.enums.ai.LlmCodeEnum;
import com.zhora.service.ai.IAiConfigService;
import dev.langchain4j.community.model.dashscope.QwenStreamingChatModel;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhehen.lu
 * @date 2025/7/29 15:07
 */
@Configuration
@RequiredArgsConstructor
public class ChatModelInitializer {

    private final IAiConfigService aiConfigService;
    private final PromptConfiguration promptConfiguration;

    @Bean
    public OpenAiStreamingChatModel deepSeekChatModel(DeepSeekChatModelConfig deepSeekChatModelConfig) {
        return OpenAiStreamingChatModel.builder()
                .baseUrl(deepSeekChatModelConfig.getBaseUrl())
                .apiKey(deepSeekChatModelConfig.getApiKey())
                .modelName(deepSeekChatModelConfig.getModelName())
                .build();
    }

    @Bean
    public AiChatAssistant deepSeekChatAssistant(OpenAiStreamingChatModel deepSeekChatModel) {
        return AiServices.builder(AiChatAssistant.class)
                .streamingChatModel(deepSeekChatModel)
                .systemMessageProvider(chatMemoryId -> promptConfiguration.getSystem())
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }

    @Bean
    public DeepSeekChatModelConfig deepSeekConfiguration() {
        DeepSeekChatModelConfig deepSeekChatModelConfig = new DeepSeekChatModelConfig();

        AiLlmConfigSearchDTO searchDTO = new AiLlmConfigSearchDTO();
        searchDTO.setCode(LlmCodeEnum.DEEP_SEEK);
        AiLlmConfigDTO deepSeek = aiConfigService.getDetail(searchDTO);
        deepSeekChatModelConfig.init(deepSeek);
        return deepSeekChatModelConfig;
    }

    @Bean
    public QwenStreamingChatModel qwenChatModel(QwenChatModelConfig qwenChatModelConfig) {
        return QwenStreamingChatModel.builder()
//                .baseUrl(qwenChatModelConfig.getBaseUrl())
                .apiKey(qwenChatModelConfig.getApiKey())
                .modelName(qwenChatModelConfig.getModelName())
                .build();
    }

    @Bean
    public AiChatAssistant qwenChatAssistant(QwenStreamingChatModel qwenStreamingChatModel) {
        return AiServices.builder(AiChatAssistant.class)
                .streamingChatModel(qwenStreamingChatModel)
                .systemMessageProvider(chatMemoryId -> promptConfiguration.getSystem())
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }

    @Bean
    public QwenChatModelConfig qwenConfiguration() {
        QwenChatModelConfig qwenChatModelConfig = new QwenChatModelConfig();

        AiLlmConfigSearchDTO searchDTO = new AiLlmConfigSearchDTO();
        searchDTO.setCode(LlmCodeEnum.Q_WEN);
        AiLlmConfigDTO deepSeek = aiConfigService.getDetail(searchDTO);
        qwenChatModelConfig.init(deepSeek);
        return qwenChatModelConfig;
    }
}
