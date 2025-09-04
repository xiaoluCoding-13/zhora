package com.zhora.common.domain.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求分页数据基础类
 *
 * @author by zhehen.lu
 * @Date 2025/8/22 14:10
 */
@Schema(description = "请求分页数据基础类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBaseReqVO {
    @Schema(description = "页数")
    private Long page = 1L;

    @Schema(description = "一页的大小")
    private Long pageSize = 10L;

    @Schema(hidden = true, description = "id大于多少")
    private Long gtId;

    @Schema(hidden = true, description = "id小于多少")
    private Long ltId;

    @Schema(description = "排序字段")
    private String orderField;

    @Schema(description = "排序方式 正序asc 倒叙desc")
    private String orderMode;
}
