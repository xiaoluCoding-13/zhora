package com.zhora.ai.v1.dto;

import com.zhora.enums.ai.ChatMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ChatDTO(@NotNull ChatMode mode, Long libraryId, @NotEmpty String message) {}
