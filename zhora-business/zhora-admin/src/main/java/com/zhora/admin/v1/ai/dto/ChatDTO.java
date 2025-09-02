package com.zhora.admin.v1.ai.dto;

import com.zhora.enums.ai.ChatMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ChatDTO(@NotNull ChatMode mode, Long libraryId, @NotEmpty String message) {}
