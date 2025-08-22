package com.ykyy.ec.common.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求分页数据基础类
 *
 * @author by sherlock
 * @Date 2022/11/4 14:10
 */
@ApiModel("请求分页数据基础类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBaseReqVO {
    @ApiModelProperty("页数")
    private Long page=1L;

    @ApiModelProperty("一页的大小")
    private Long pageSize=10L;

    @ApiModelProperty(hidden = true, value = "id大于多少")
    private Long gtId;

    @ApiModelProperty(hidden = true, value = "id小于多少")
    private Long ltId;

    @ApiModelProperty("排序字段")
    private String orderField;

    @ApiModelProperty("排序方式 正序asc 倒叙desc")
    private String orderMode;
}
