package com.zhora.admin.v1.ai.business.impl;

import com.zhora.admin.v1.ai.dto.ChatDTO;
import com.zhora.admin.v1.ai.business.AiChatAssistant;
import com.zhora.admin.v1.ai.business.SystemToolAssistant;
import com.zhora.common.exception.ServiceException;
import com.zhora.entity.ai.AiLlmConfigEntity;
import com.zhora.enums.ai.LlmCodeEnum;
import com.zhora.service.ai.IAiLlmConfigService;
import dev.langchain4j.service.TokenStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author zhehen.lu
 * @date 2025/8/22 15:11
 */

@Service
@Slf4j
public class AiChatService {

    @Autowired
    private IAiLlmConfigService aiConfigService;

    @Autowired
    @Qualifier("deepSeekChatAssistant")
    private AiChatAssistant deepSeekChatAssistant;

    @Autowired
    @Qualifier("qwenChatAssistant")
    private AiChatAssistant qwenChatAssistant;

    @Autowired
    private SystemToolAssistant deepSeekToolAssistant;

    @Autowired
    private SystemToolAssistant qwenToolAssistant;

    public TokenStream actionPrecedenceExecuteWith(String sessionIdentifier, String userMessage) {
        LlmCodeEnum code = getPrecedenceLlmCode();
        return switch (code) {
            case DEEP_SEEK -> deepSeekToolAssistant.ask(sessionIdentifier, userMessage);
            case Q_WEN -> qwenToolAssistant.ask(sessionIdentifier, userMessage);
            default -> throw new ServiceException(String.format("无效的模型代码 %s", code));
        };
    }

    public TokenStream chat(String sessionIdentifier, ChatDTO chatDto) {
        return switch (chatDto.mode()) {
            case NORMAL -> chatWithPrecedenceLlm(sessionIdentifier, chatDto);
            case WITH_LIBRARY -> chatWithLibrary(chatDto.libraryId(), chatDto);
        };
    }

    public TokenStream chatWithLibrary(Long libraryId, ChatDTO chatDto) {
//        return zhiPuChatAssistant.chat(String.valueOf(libraryId), chatDto.message());
        return null;
    }

    public TokenStream chatWithPrecedenceLlm(String sessionIdentifier, ChatDTO chatDto) {
        LlmCodeEnum code = getPrecedenceLlmCode();
        String userMessage = chatDto.message();
        return switch (code) {
            case DEEP_SEEK -> deepSeekChatAssistant.chat(sessionIdentifier, userMessage);
            case Q_WEN -> qwenChatAssistant.chat(sessionIdentifier, userMessage);
            default -> throw new ServiceException(String.format("无效的模型代码 %s", code));
        };
    }

    public void evictChatMemory(String sessionIdentifier) {
        deepSeekChatAssistant.evictChatMemory(sessionIdentifier);
        qwenChatAssistant.evictChatMemory(sessionIdentifier);
        deepSeekToolAssistant.evictChatMemory(sessionIdentifier);
        qwenToolAssistant.evictChatMemory(sessionIdentifier);
    }

    private LlmCodeEnum getPrecedenceLlmCode() {
        Optional<AiLlmConfigEntity> aiConfigEntity = aiConfigService.getPrecedenceChatLlmBy(true);
        AiLlmConfigEntity aiLlmConfig = aiConfigEntity.orElseThrow(() -> new ServiceException("没有开启的大模型"));
        return aiLlmConfig.getCode();
    }

}
