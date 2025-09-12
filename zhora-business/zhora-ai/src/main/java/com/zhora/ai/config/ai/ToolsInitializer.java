package com.zhora.ai.config.ai;

import com.zhora.ai.component.UserRolePermissionOperatorTool;
import com.zhora.ai.v1.business.ISystemToolAssistant;
import dev.langchain4j.community.model.dashscope.QwenStreamingChatModel;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ToolsInitializer {

  private final UserRolePermissionOperatorTool userRolePermissionOperatorTool;

  @Bean
  public ISystemToolAssistant deepSeekToolAssistant(OpenAiStreamingChatModel deepSeekChatModel) {
    return AiServices.builder(ISystemToolAssistant.class)
        .streamingChatModel(deepSeekChatModel)
        .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
        .tools(userRolePermissionOperatorTool)
        .build();
  }

  @Bean
  public ISystemToolAssistant qwenToolAssistant(QwenStreamingChatModel qwenStreamingChatModel) {
    return AiServices.builder(ISystemToolAssistant.class)
            .streamingChatModel(qwenStreamingChatModel)
            .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
        .tools(userRolePermissionOperatorTool)
            .build();
  }
}
