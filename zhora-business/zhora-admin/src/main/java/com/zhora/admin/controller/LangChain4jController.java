package com.zhora.admin.controller;

import com.zhora.admin.service.Assistant;
import com.zhora.admin.service.LangChain4jService;
import com.zhora.admin.service.SteamAssistant;
import dev.langchain4j.data.message.*;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.util.Base64;
import java.util.List;

/**
 * @author zhehen.lu
 * @date 2025/7/27 15:47
 */
@RestController
@CrossOrigin
@RequestMapping("/langchain4jChat")
public class LangChain4jController {

    @Autowired
    OpenAiStreamingChatModel deepSeekChatModel;
//    @Autowired
//    EmbeddingModel embeddingModel;
//    @Autowired
//    StreamingChatModel streamingChatModel;
//    @Autowired
//    Assistant assistant;
//    @Autowired
//    SteamAssistant steamAssistant;
//    @Autowired
//    LangChain4jService langChain4jService;
//    @Autowired
//    ChatModel chatModel;



    /**
     * 聊天
     *
     * @param message
     * @return
     */
//    @GetMapping("/assistant")
//    public String assistant(@RequestParam(value = "message", defaultValue = "What is the time now?") String message) {
//        return chatModel.chat(new SystemMessage("请全部用英语回答"), new UserMessage(message)).aiMessage().text();
//    }

    /**
     * 流式访问
     *
     * @return
     */
    @GetMapping(value = "/steamAssistant", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> steamAssistant(@RequestParam(value = "message", defaultValue = "What is the time now?") String message) {
        return Flux.create(emitter -> {
            deepSeekChatModel.chat(message, new StreamingChatResponseHandler() {

                @Override
                public void onPartialResponse(String s) {
                    emitter.next(s); // 发送
                }

                @Override
                public void onCompleteResponse(ChatResponse chatResponse) {
                    emitter.complete(); // 完成流
                }

                @Override
                public void onError(Throwable error) {
                    emitter.error(error);
                }
            });
        });
    }


    /**
     * 有记忆的问答
     *
     * @param message
     * @return
     */
//    @GetMapping("/assistant2")
//    public String assistant2(@RequestParam(value = "message", defaultValue = "What is the time now?") String message) {
//        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
//        chatMemory.add(new UserMessage(message));
//        String result = chatModel.chat(chatMemory.messages()).aiMessage().text();
//        chatMemory.add(new AiMessage(result));
//        return result;
//    }


//    @PostMapping("/assistant4")
//    public String assistant4(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "message", defaultValue = "What is the time now?") String message) {
//
//        String Base64Image = null;
//        try {
//            Base64Image = Base64.getEncoder().encodeToString(file.getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (Base64Image != null) {
//            List<Content> contents = new java.util.ArrayList<>();
//            contents.add(new TextContent(message));
//            // 文件的格式
//            String mimeType = "jpg";
//            contents.add(new ImageContent(Base64Image, mimeType));
//
//            return chatModel.chat(new UserMessage(contents)).aiMessage().text();
//        }
//
//
//        return "success";
//    }


//    @GetMapping("/assistant3")
//    public String assistant3(@RequestParam(value = "memoryId", defaultValue = "1") String memoryId, @RequestParam(value = "message", defaultValue = "What is the time now?") String message) {
//
//        String result = assistant.chat("1",message);
//
//        return result;
//    }


    /**
     * 生成embedding 属据*
     * @param message
     * @return
     */
//    @GetMapping("/embd")
//    public String embd(@RequestParam(value = "message", defaultValue = "What is the time now?") String message) {
//        return embeddingModel.embed(message).content().toString();
//    }


//    @GetMapping("/embd2")
//    public String embd2(@RequestParam(value = "message", defaultValue = "What is the time now?") String message) {
//        return langChain4jService.search(message);
//    }

}
