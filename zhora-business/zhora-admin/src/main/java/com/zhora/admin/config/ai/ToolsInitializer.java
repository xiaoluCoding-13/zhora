package com.zhora.admin.config.ai;

import com.zhora.admin.service.SystemToolAssistant;
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

  @Bean
  public SystemToolAssistant deepSeekToolAssistant(OpenAiStreamingChatModel deepSeekChatModel) {
    return AiServices.builder(SystemToolAssistant.class)
        .streamingChatModel(deepSeekChatModel)
        .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
//        .tools(userRolePermissionOperatorTool, departmentOperatorTool, positionOperatorTool)
        .build();
  }

  @Bean
  public SystemToolAssistant qwenToolAssistant(QwenStreamingChatModel qwenStreamingChatModel) {
    return AiServices.builder(SystemToolAssistant.class)
            .streamingChatModel(qwenStreamingChatModel)
            .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
//        .tools(userRolePermissionOperatorTool, departmentOperatorTool, positionOperatorTool)
            .build();
  }
}
