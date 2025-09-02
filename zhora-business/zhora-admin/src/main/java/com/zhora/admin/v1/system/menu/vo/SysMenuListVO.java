package com.zhora.admin.v1.system.menu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

/**
 * 菜单
 *
 * @author zhehen.lu
 * @date 2025/8/29 14:03
 */
@Data
public class SysMenuListVO {

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params;
}
