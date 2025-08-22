package com.ykyy.ec.common.page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ykyy.jackson.serializer.JacksonLongSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页数据基础结果
 *
 * @author by sherlock
 * @Date 2022/11/4 14:11
 */
@ApiModel("分页数据基础结果集")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDataGridRespDTO<T> {
//    @JsonSerialize(using = JacksonLongSerializer.class)
    @ApiModelProperty("总记录数")
    private Long total = 0L;

    @JsonSerialize(using = JacksonLongSerializer.class)
    @ApiModelProperty("当前页数")
    private Long current = 0L;

    @JsonSerialize(using = JacksonLongSerializer.class)
    @ApiModelProperty("当前分页数目")
    private Long pageSize = 0L;

    @ApiModelProperty(value = "当前页数", required = true)
    private List<T> list = new ArrayList<>();
}
