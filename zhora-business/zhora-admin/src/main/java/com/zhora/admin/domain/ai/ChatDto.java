package com.zhora.admin.domain.ai;

import com.zhora.enums.ai.ChatMode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ChatDto(@NotNull ChatMode mode, Long libraryId, @NotEmpty String message) {}
