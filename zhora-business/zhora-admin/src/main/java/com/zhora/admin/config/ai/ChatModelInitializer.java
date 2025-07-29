package com.zhora.admin.config.ai;

import com.zhora.admin.component.PromptConfiguration;
import com.zhora.admin.config.ai.model.DeepSeekChatModelConfig;
import com.zhora.dto.ai.AiConfigDTO;
import com.zhora.dto.ai.AiConfigSearchDTO;
import com.zhora.enums.ai.CodeEnum;
import com.zhora.service.ai.IAiConfigService;
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

        AiConfigSearchDTO searchDTO = new AiConfigSearchDTO();
        searchDTO.setCode(CodeEnum.DEEP_SEEK);
        AiConfigDTO deepSeek = aiConfigService.getDetail(searchDTO);
        deepSeekChatModelConfig.init(deepSeek);
        return deepSeekChatModelConfig;
    }
}
