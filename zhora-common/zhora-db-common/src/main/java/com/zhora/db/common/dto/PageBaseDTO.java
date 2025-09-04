package com.zhora.db.common.dto;

import com.zhora.common.domain.page.PageBaseReqVO;
import lombok.Data;

/**
 * 分页基础数据 跟 {@link PageBaseReqVO}保持一致
 *
 * @author by zhehen.lu
 * @date 2023/2/22 10:31
 */
@Data
public class PageBaseDTO {
    /**
     * 页数
     */
    private Long page = 1L;

    /**
     * 一页的大小
     */
    private Long pageSize = 10L;

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
