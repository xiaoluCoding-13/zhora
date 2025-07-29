package com.zhora.enums.ai;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 
 * @author zhehen.lu
 * @date 2025/7/29 13:56
 */
@Getter
@AllArgsConstructor
public enum TypeEnum {

    LLM("LLM"),

    CHAT("CHAT"),

    EMBEDDING("EMBEDDING");

    private final String value;

    public static Boolean checkValue(String value) {
        return Arrays.stream(TypeEnum.values())
                .anyMatch(e -> Objects.equals(e.getValue(), value));
    }

    public static TypeEnum getEnumByValue(String value) {
        if (value == null) {
            return null;
        }

        return Arrays.stream(TypeEnum.values())
                .filter(v -> v.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }
}
