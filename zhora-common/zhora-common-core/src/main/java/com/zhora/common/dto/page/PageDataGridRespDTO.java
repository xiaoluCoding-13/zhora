package com.zhora.common.dto.page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhora.common.jackson.serializer.JacksonLongSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页数据基础结果
 *
 * @author by zhehen.lu
 * @Date 2025/8/22 14:11
 */
@Schema(description = "分页数据基础结果集")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDataGridRespDTO<T> {
//    @JsonSerialize(using = JacksonLongSerializer.class)
    @Schema(description = "总记录数")
    private Long total = 0L;

    @JsonSerialize(using = JacksonLongSerializer.class)
    @Schema(description = "当前页数")
    private Long current = 0L;

    @JsonSerialize(using = JacksonLongSerializer.class)
    @Schema(description = "当前分页数目")
    private Long pageSize = 0L;

    @Schema(description = "当前页数", required = true)
    private List<T> list = new ArrayList<>();
}
