package com.ykyy.ec.db.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字段属性查询包装增强
 *
 * @author by sherlock
 * @date 2023/8/23 9:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldSearchWrapperDTO<T> {
    /**
     * 具体的查询的值
     */
    private T value;

    /**
     * 操作类型
     */
    private OperateTypeEnum operateType;

    /**
     * 操作枚举类
     * @return {@link null}
     * @date 2023/8/23 9:05
     * @author sherlock
     */
    public enum OperateTypeEnum {
        EQ,
        NEQ,
        LIKE,
        GT,
        GTE,
        LT,
        LTE
        ;
    }
}
