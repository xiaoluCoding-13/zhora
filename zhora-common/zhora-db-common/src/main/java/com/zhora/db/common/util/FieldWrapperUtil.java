package com.ykyy.ec.db.util;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ykyy.ec.db.dto.FieldSearchWrapperDTO;

import java.util.Optional;

/**
 * 字段包装工具类
 *
 * @author by sherlock
 * @date 2023/8/23 14:39
 */
public class FieldWrapperUtil {

    /**
     * 设置条件包装
     * @param wrapper                   条件包装条件
     * @param fieldSearchWrapperDTO     字段搜索包装模型
     * @param entityField               实体字段
     * @date 2023/8/23 14:47
     * @author sherlock
     */
    public static <T, S, R> void setWhereWrapper(LambdaQueryChainWrapper<T> wrapper, FieldSearchWrapperDTO<S> fieldSearchWrapperDTO,
                                              SFunction<T, R> entityField) {
        Optional.ofNullable(fieldSearchWrapperDTO)
                .ifPresent(fieldWrapper -> {
                    Optional.ofNullable(fieldWrapper.getOperateType())
                            .ifPresent(operateTypeEnum -> {
                                switch (operateTypeEnum) {
                                    case EQ:
                                        wrapper.eq(entityField, fieldWrapper.getValue());
                                        break;
                                    case NEQ:
                                        wrapper.ne(entityField, fieldWrapper.getValue());
                                        break;
                                    case GT:
                                        wrapper.gt(entityField, fieldWrapper.getValue());
                                        break;
                                    case GTE:
                                        wrapper.ge(entityField, fieldWrapper.getValue());
                                        break;
                                    case LT:
                                        wrapper.lt(entityField, fieldWrapper.getValue());
                                        break;
                                    case LTE:
                                        wrapper.le(entityField, fieldWrapper.getValue());
                                        break;
                                    case LIKE:
                                        wrapper.like(entityField, fieldWrapper.getValue());
                                        break;
                                }
                            });
                });

    }

    /**
     * 设置条件包装
     * @param wrapper                   条件包装条件
     * @param fieldSearchWrapperDTO     字段搜索包装模型
     * @param entityField               实体字段
     * @date 2023/12/25 13:06
     * @author sherlock
     */
    public static <T, S, R> void setWhereWrapper(LambdaQueryWrapper<T> wrapper, FieldSearchWrapperDTO<S> fieldSearchWrapperDTO,
                                                 SFunction<T, R> entityField) {
        Optional.ofNullable(fieldSearchWrapperDTO)
                .ifPresent(fieldWrapper -> {
                    Optional.ofNullable(fieldWrapper.getOperateType())
                            .ifPresent(operateTypeEnum -> {
                                switch (operateTypeEnum) {
                                    case EQ:
                                        wrapper.eq(entityField, fieldWrapper.getValue());
                                        break;
                                    case NEQ:
                                        wrapper.ne(entityField, fieldWrapper.getValue());
                                        break;
                                    case GT:
                                        wrapper.gt(entityField, fieldWrapper.getValue());
                                        break;
                                    case GTE:
                                        wrapper.ge(entityField, fieldWrapper.getValue());
                                        break;
                                    case LT:
                                        wrapper.lt(entityField, fieldWrapper.getValue());
                                        break;
                                    case LTE:
                                        wrapper.le(entityField, fieldWrapper.getValue());
                                        break;
                                    case LIKE:
                                        wrapper.like(entityField, fieldWrapper.getValue());
                                        break;
                                }
                            });
                });

    }
}
