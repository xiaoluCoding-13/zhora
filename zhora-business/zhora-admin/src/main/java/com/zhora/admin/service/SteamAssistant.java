package com.zhora.admin.service;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

@AiService
public interface SteamAssistant {
    Flux<String> chat(@UserMessage String userMessage);

}
