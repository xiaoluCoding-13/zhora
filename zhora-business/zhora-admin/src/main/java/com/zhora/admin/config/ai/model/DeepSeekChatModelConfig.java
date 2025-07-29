package com.zhora.admin.config.ai.model;

import com.zhora.dto.ai.AiConfigDTO;
import lombok.Data;

/**
 * @author zhehen.lu
 * @date 2025/7/29 15:08
 */
@Data
public class DeepSeekChatModelConfig {

    private String baseUrl;

    private String apiKey;

    private String modelName;

    public void init(AiConfigDTO configDTO) {
        this.baseUrl = configDTO.getUrl();
        this.apiKey = configDTO.getApiKey();
        this.modelName = configDTO.getModelName();
    }
}
