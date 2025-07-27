package com.zhora.admin.service;


import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.AUTOMATIC;

@AiService(
        wiringMode = AUTOMATIC,
        chatMemory = "chatMemory",
        chatMemoryProvider = "chatMemoryProvider"
)
public interface Assistant {

    String chat(@MemoryId String memoryId, @UserMessage String userMessage);

}
