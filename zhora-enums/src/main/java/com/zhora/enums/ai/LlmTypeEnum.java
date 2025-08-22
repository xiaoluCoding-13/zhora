package com.zhora.enums.ai;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 
 * @author zhehen.lu
 * @date 2025/8/22 13:56
 */
@Getter
@AllArgsConstructor
public enum LlmTypeEnum {

    LLM("LLM"),

    CHAT("CHAT"),

    EMBEDDING("EMBEDDING");

    private final String value;

    public static Boolean checkValue(String value) {
        return Arrays.stream(LlmTypeEnum.values())
                .anyMatch(e -> Objects.equals(e.getValue(), value));
    }

    public static LlmTypeEnum getEnumByValue(String value) {
        if (value == null) {
            return null;
        }

        return Arrays.stream(LlmTypeEnum.values())
                .filter(v -> v.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }
}
