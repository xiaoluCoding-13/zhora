package com.zhora.admin.config.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.memory.ChatMemoryAccess;

public interface SystemToolAssistant extends ChatMemoryAccess {

  /**
   * 会话ID的问答
   */
  TokenStream ask(@MemoryId String memoryId, @UserMessage String question);

  /**
   * 无会话ID的问答
   */
  TokenStream ask(@UserMessage String question);
}
