package com.zhora.admin.v1.ai.store;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhehen.lu
 * @date 2025/8/22 15:43
 */
@Component
public class InMemoryChatMemoryStore implements ChatMemoryStore {

    HashMap<String, List<ChatMessage>> memory = new HashMap<>();

    @Override
    public List<ChatMessage> getMessages(Object o) {
        if (memory.containsKey(o.toString())) {
            return memory.get(o.toString());
        }
        return new LinkedList<>();
    }

    @Override
    public void updateMessages(Object o, List<ChatMessage> list) {

        memory.put(o.toString(), list);

    }

    @Override
    public void deleteMessages(Object o) {

        memory.remove(o.toString());
    }
}