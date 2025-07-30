package com.zhora.admin.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.memory.ChatMemoryAccess;

public interface SystemToolAssistant extends ChatMemoryAccess {
  /**
   * 会话ID的问答
   */
  TokenStream ask(@MemoryId String memoryId, @UserMessage String question);
}
