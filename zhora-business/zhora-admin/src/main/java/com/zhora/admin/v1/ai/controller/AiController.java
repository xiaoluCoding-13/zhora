package com.zhora.admin.v1.ai.controller;

import com.zhora.admin.v1.ai.dto.ChatDTO;
import com.zhora.admin.v1.ai.business.impl.AiChatService;
import com.zhora.admin.v1.ai.business.impl.RagService;
import com.zhora.common.dto.ResponseDTO;
import com.zhora.common.exception.ServiceException;
import com.zhora.dto.ai.AiLlmConfigDTO;
import com.zhora.dto.ai.AiLlmConfigSearchDTO;
import com.zhora.enums.ai.LlmCodeEnum;
import com.zhora.service.ai.IAiLlmConfigService;
import dev.langchain4j.service.TokenStream;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.Map;

/**
 * @author zhehen.lu
 * @date 2025/8/22 15:47
 */
@RestController
@CrossOrigin
@RequestMapping("/ai/v1")
@Slf4j
@Tag(name = "ai大模型相关")
public class AiController {

    @Autowired
    private IAiLlmConfigService aiLlmConfigService;

    @Autowired
    private AiChatService aiChatService;

    @Autowired
    private RagService ragService;

    @PostMapping(value = "/action/execute", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> actionExecute(@RequestBody String userMessage) {
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();
        TokenStream chat = aiChatService.actionPrecedenceExecuteWith("用户ID123456", userMessage);
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
    public Flux<String> chat(@RequestBody ChatDTO chatDto) {
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

    @PostMapping("/action/search")
    public Map<String, String> searchAction(@RequestBody String message) {
        AiLlmConfigSearchDTO searchDTO = new AiLlmConfigSearchDTO();
        searchDTO.setCode(LlmCodeEnum.Q_WEN);
        AiLlmConfigDTO aiLlmConfig = aiLlmConfigService.getDetail(searchDTO);
        if (!aiLlmConfig.getEnable()) {
            throw new ServiceException("命令模型未启用，请开启后再试。");
        }
        return ragService.searchAction(message);
    }

    @PostMapping("/chat/refresh")
    public ResponseDTO<Void> createNewConversation() {
        aiChatService.evictChatMemory("用户ID123456");
        return ResponseDTO.success();
    }

}
