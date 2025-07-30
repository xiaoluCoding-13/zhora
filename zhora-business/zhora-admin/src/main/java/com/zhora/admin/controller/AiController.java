package com.zhora.admin.controller;

import com.zhora.admin.domain.ai.ChatDto;
import com.zhora.admin.service.impl.AiChatService;
import com.zhora.common.dto.ResponseDTO;
import com.zhora.entity.ai.AiLlmConfigEntity;
import com.zhora.service.ai.IAiConfigService;
import dev.langchain4j.service.TokenStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.Optional;

/**
 * @author zhehen.lu
 * @date 2025/7/27 15:47
 */
@RestController
@CrossOrigin
@RequestMapping("/ai")
@Slf4j
public class AiController {
    @Autowired
    private IAiConfigService aiConfigService;

    @Autowired
    private AiChatService aiChatService;

    @GetMapping("/getPrecedenceChatLlmBy")
    public ResponseDTO<Optional<AiLlmConfigEntity>> getPrecedenceChatLlmBy() {
        return ResponseDTO.success(aiConfigService.getPrecedenceChatLlmBy(true));
    }

    @PostMapping(value = "/action/execute", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> actionExecute(@RequestBody String userMessage) {
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();
        TokenStream chat = aiChatService.actionPrecedenceExecuteWith("用户ID123456",userMessage);
        chat.onPartialResponse(
                        (text) -> {
                            log.debug("ai 响应结果: {}", text);
                            sink.tryEmitNext(
                                    StringUtils.isNotEmpty(text) ? text.replace(" ", "␣").replace("\t", "⇥") : "");
                        })
                .onToolExecuted(
                        toolExecution -> log.debug("当前请求 {} 成功执行函数调用: {}", userMessage, toolExecution))
                .onCompleteResponse(
                        r -> {
                            log.debug("ai 流式输出结束: {}", r);
                            sink.tryEmitComplete();
                            sink.emitComplete(Sinks.EmitFailureHandler.FAIL_FAST);
                        })
                .onError(
                        (e) -> {
                            sink.tryEmitError(e);
                            sink.tryEmitComplete();
                            sink.emitComplete(Sinks.EmitFailureHandler.FAIL_FAST);
                        })
                .start();
        return sink.asFlux().timeout(Duration.ofSeconds(120));
    }

    @PostMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chat(@RequestBody ChatDto chatDto) {
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();
        TokenStream chat = aiChatService.chat("用户ID123456", chatDto);
        chat.onPartialResponse(
                        text ->
                                sink.tryEmitNext(
                                        StringUtils.isNotEmpty(text) ? text.replace(" ", "␣").replace("\t", "⇥") : ""))
                .onCompleteResponse(
                        r -> {
                            sink.tryEmitComplete();
                            sink.emitComplete(Sinks.EmitFailureHandler.FAIL_FAST);
                        })
                .onError(sink::tryEmitError)
                .start();
        return sink.asFlux().timeout(Duration.ofSeconds(120));
    }

    @PostMapping("/chat/refresh")
    public ResponseDTO<Void> createNewConversation() {
        aiChatService.evictChatMemory("用户ID123456");
        return ResponseDTO.success();
    }

}
