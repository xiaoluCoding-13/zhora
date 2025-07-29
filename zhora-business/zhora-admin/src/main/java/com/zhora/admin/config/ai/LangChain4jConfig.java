package com.zhora.admin.config.ai;

import com.zhora.admin.store.InMemoryChatMemoryStore;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhehen.lu
 * @date 2025/7/27 15:41
 */
@Configuration
public class LangChain4jConfig {
    @Resource
    InMemoryChatMemoryStore inMemoryChatMemoryStore;

    @Bean
    ChatMemory chatMemory() {
        //设置聊天记忆记录的message数量
        return MessageWindowChatMemory.withMaxMessages(10);
    }

    @Bean
    public OpenAiStreamingChatModel deepSeekChatModel() {
        return OpenAiStreamingChatModel.builder()
                .baseUrl("https://api.deepseek.com")
                .apiKey("sk-049b7f9b70854c8f89049fe9c48e76c4")
                .modelName("deepseek-chat")
                .build();
    }


    @Bean
    ChatMemoryProvider chatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(10)
                .chatMemoryStore(inMemoryChatMemoryStore)
                .build();
    }
}
