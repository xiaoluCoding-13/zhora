package com.zhora.entity.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 *  字典数据
 *
 * @author zhehen.lu
 */
@Data
public class DictData {
    @JsonIgnore
    private Long dictTypeId;
    private String dictLabel;
    private String dictValue;
}
