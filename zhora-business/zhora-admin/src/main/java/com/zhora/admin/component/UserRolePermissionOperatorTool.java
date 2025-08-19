package com.zhora.admin.component;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.model.output.structured.Description;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 用户相关
 *
 * @author zhehen.lu
 * @date 2025/8/18 17:18
 */
@Description("和用户管理有关的操作工具")
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
@RequiredArgsConstructor
@Component
@Slf4j
public class UserRolePermissionOperatorTool {

    @Tool(value = {"创建用户", "入职申请", "开通账号"})
    void createUser(@P(value = "用户名") @Size(min = 1, max = 15) String name) {
        log.info("创建用户！");
    }
}
