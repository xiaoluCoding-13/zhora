package com.ykyy.ec.db.dto;

import lombok.Data;

/**
 * 分页基础数据 跟 {@link com.ykyy.ec.common.page.PageBaseReqVO}保持一致
 *
 * @author by sherlock
 * @date 2023/2/22 10:31
 */
@Data
public class PageBaseDTO {
    /**
     * 页数
     */
    private Long page=1L;

    /**
     * 一页的大小
     */
    private Long pageSize=10L;

    /**
     * id大于多少
     */
    private Long gtId;

    /**
     * id小于多少
     */
    private Long ltId;

    /**
     * 排序字段
     */
    private String orderField;

    /**
     * 排序方式 正序asc 倒叙desc
     */
    private String orderMode;
}
