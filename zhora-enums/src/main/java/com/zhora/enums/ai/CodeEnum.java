package com.zhora.enums.ai;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 
 * @author zhehen.lu
 * @date 2025/7/29 13:55
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {

    DEEP_SEEK("DEEP_SEEK"),

    ZHI_PU("ZHI_PU"),

    ZHI_PU_EMBEDDING("ZHI_PU_EMBEDDING");

    private final String value;

    public static Boolean checkValue(String value) {
        return Arrays.stream(CodeEnum.values())
                .anyMatch(e -> Objects.equals(e.getValue(), value));
    }

    public static CodeEnum getEnumByValue(String value) {
        if (value == null) {
            return null;
        }

        return Arrays.stream(CodeEnum.values())
                .filter(v -> v.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }
}
