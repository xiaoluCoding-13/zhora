package com.zhora.admin.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.memory.ChatMemoryAccess;

public interface AiChatAssistant extends ChatMemoryAccess {
  TokenStream chat(@MemoryId String memoryId, @UserMessage String userMessage);
}
