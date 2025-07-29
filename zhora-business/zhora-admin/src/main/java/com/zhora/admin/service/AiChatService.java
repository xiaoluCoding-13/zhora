package com.zhora.admin.service;

import com.zhora.admin.config.ai.SystemToolAssistant;
import com.zhora.common.exception.ServiceException;
import com.zhora.entity.ai.AiConfigEntity;
import com.zhora.enums.ai.CodeEnum;
import com.zhora.service.ai.IAiConfigService;
import dev.langchain4j.service.TokenStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author zhehen.lu
 * @date 2025/7/29 15:11
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class AiChatService {

    private final IAiConfigService aiConfigService;
    private final SystemToolAssistant deepSeekToolAssistant;

    public TokenStream actionPrecedenceExecuteWith(String sessionIdentifier, String userMessage) {
        CodeEnum code = getPrecedenceLlmCode();
        return switch (code) {
            case DEEP_SEEK -> deepSeekToolAssistant.ask(sessionIdentifier, userMessage);
            default -> throw new ServiceException(String.format("无效的模型代码 %s", code));
        };
    }

    public TokenStream actionPrecedenceExecuteWith(String userMessage) {
        CodeEnum code = getPrecedenceLlmCode();
        return switch (code) {
            case DEEP_SEEK -> deepSeekToolAssistant.ask(userMessage);
            default -> throw new ServiceException(String.format("无效的模型代码 %s", code));
        };
    }

    public void evictChatMemory(String sessionIdentifier) {
        deepSeekToolAssistant.evictChatMemory(sessionIdentifier);
    }

    private CodeEnum getPrecedenceLlmCode() {
        Optional<AiConfigEntity> aiConfigEntity = aiConfigService.getPrecedenceChatLlmBy(true);
        AiConfigEntity aiLlmConfig = aiConfigEntity.orElseThrow(() -> new ServiceException("没有开启的大模型"));
        return aiLlmConfig.getCode();
    }

}
