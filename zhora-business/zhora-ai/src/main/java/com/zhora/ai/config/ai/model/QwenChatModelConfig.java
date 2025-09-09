package com.zhora.ai.config.ai.model;

import com.zhora.dto.ai.AiLlmConfigDTO;
import lombok.Data;

/**
 * @author zhehen.lu
 * @date 2025/8/22 15:08
 */
@Data
public class QwenChatModelConfig {

    private String baseUrl;

    private String apiKey;

    private String modelName;

    public void init(AiLlmConfigDTO configDTO) {
        this.baseUrl = configDTO.getUrl();
        this.apiKey = configDTO.getApiKey();
        this.modelName = configDTO.getModelName();
    }
}
