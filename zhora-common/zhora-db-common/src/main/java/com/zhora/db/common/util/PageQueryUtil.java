package com.zhora.db.common.util;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzh.db.properties.MybatisPlusAutoFillProperties;
import com.zhora.common.utils.ApplicationContextUtil;
import com.zhora.db.common.dto.PageBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Classname PageQueryUtil
 * @Description 页面查询工具
 * @date 2025/8/22 16:01
 * @Created by zhehen.lu
 */
@AllArgsConstructor
@Builder
public class PageQueryUtil<T, S extends PageBaseDTO> {
    /**
     * 排序列表
     */
    @Getter
    private List<OrderItem> orderList = new ArrayList<>();

    /**
     * 页数
     */
    @Getter
    @Setter
    private Long page = 1L;

    /**
     * 一页读取大小
     */
    @Getter
    @Setter
    private Long pageSize = 10L;

    /**
     * 是否查询count
     */
    @Getter
    @Setter
    private Boolean searchCount = true;

    /**
     * 排序字段
     */
    @Getter
    private String orderField;

    /**
     * 排序方式 asc desc
     */
    @Getter
    private String orderMode;

    private Class<T> entity;

    /**
     * type : 0以pageBaseReqVO参数传入优先（没传入排序字段时）  1 默认用户调用OrderItem优先 2 pageBaseReqVO优先没传默认以主键排倒叙
     */
    private TypeEnum type;

    public PageQueryUtil() {
        this(null);
    }

    public PageQueryUtil(Class<T> entityClass) {
        this(entityClass, TypeEnum.PARAM_PRIORITY);
    }

    public PageQueryUtil(Class<T> entity, TypeEnum type) {
        this(entity, type, null);
    }

    public PageQueryUtil(Class<T> entity, TypeEnum type, S pageBaseReq) {
        this.entity = entity;
        this.type = type;
        Optional.ofNullable(pageBaseReq)
                .ifPresent(v -> setPageBaseReq(v));
    }

    /**
     * 构建分页对象
     *
     * @param
     * @return {@link IPage<T>}
     * @date 2023/1/31 10:38
     * @author zhehen.lu
     */
    public IPage<T> buildPageObj() {
        Page<T> pageObj = new Page<>(page, pageSize);

        switch (type) {
            case PARAM_PRIORITY:
            case PARAM_PRIORITY_DEFAULT_PRIMARY:
            case PARAM_PRIORITY_DEFAULT_UPDATE_TIME:
            case PARAM_PRIORITY_DEFAULT_CREATE_TIME:
                if (isPageBaseReqHaveOrder()) {
                    // 设置了实体， 并且传入了排序字段
                    boolean match = isExistOrderField();
                    if (match) {
                        orderList = new ArrayList<>();
                        setOrderFieldItem();
                    }
                }

                // 20230802 就算传入了其它排序字段， 第二排序字段也排序id
                if (type == TypeEnum.PARAM_PRIORITY_DEFAULT_PRIMARY && entity != null) {
                    // 设置主键倒叙
                    Field primaryKey = Arrays.stream(entity.getDeclaredFields())
                            .filter(field -> field.isAnnotationPresent(TableId.class))
                            .findFirst()
                            .orElse(null);
                    Optional.ofNullable(primaryKey)
                            .ifPresent(field -> setOrderItem(field.getName(), false));
                } else if (type == TypeEnum.PARAM_PRIORITY_DEFAULT_UPDATE_TIME && entity != null) {
                    // 反射拿更新字段和创建字段不好拿
                    MybatisPlusAutoFillProperties properties = ApplicationContextUtil.getBean(MybatisPlusAutoFillProperties.class);

                    Arrays.stream(entity.getDeclaredFields())
                            .filter(field -> field.getName().equalsIgnoreCase(properties.getUpdateTimeField()))
                            .findFirst()
                            .ifPresent(field -> setOrderItem(field.getName(), false));

                    // 2023-05-19 变更， 第二排序字段， 根据主键id
                    Field primaryKey = Arrays.stream(entity.getDeclaredFields())
                            .filter(field -> field.isAnnotationPresent(TableId.class))
                            .findFirst()
                            .orElse(null);
                    Optional.ofNullable(primaryKey)
                            .ifPresent(field -> setOrderItem(field.getName(), false));

//                    Arrays.stream(entity.getDeclaredFields())
//                            .filter(field -> field.getName().equalsIgnoreCase(properties.getCreateTimeField()))
//                            .findFirst()
//                            .ifPresent(field -> setOrderItem(field.getName(), false));


//                    setOrderItem("updateTime", false);
//                    setOrderItem("createTime", false);
                } else if (type == TypeEnum.PARAM_PRIORITY_DEFAULT_CREATE_TIME && entity != null) {
                    // 反射拿创建字段不好拿
                    MybatisPlusAutoFillProperties properties = ApplicationContextUtil.getBean(MybatisPlusAutoFillProperties.class);

                    Arrays.stream(entity.getDeclaredFields())
                            .filter(field -> field.getName().equalsIgnoreCase(properties.getCreateTimeField()))
                            .findFirst()
                            .ifPresent(field -> setOrderItem(field.getName(), false));

                    Field primaryKey = Arrays.stream(entity.getDeclaredFields())
                            .filter(field -> field.isAnnotationPresent(TableId.class))
                            .findFirst()
                            .orElse(null);
                    Optional.ofNullable(primaryKey)
                            .ifPresent(field -> setOrderItem(field.getName(), false));
                }
                break;
            case ORDER_ITEM_PRIORITY:
                if (CollectionUtils.isEmpty(orderList) && isPageBaseReqHaveOrder()) {
                    boolean match = isExistOrderField();
                    if (match) {
                        setOrderFieldItem();
                    }
                }
                break;
        }

        pageObj.setSearchCount(searchCount).addOrder(orderList);

        return pageObj;
    }

    /**
     * 设置分页的信息
     *
     * @param pageBaseReq
     * @date 2023/1/31 10:38
     * @author zhehen.lu
     */
    public void setPageBaseReq(S pageBaseReq) {
        page = pageBaseReq.getPage();
        pageSize = pageBaseReq.getPageSize();
        orderField = pageBaseReq.getOrderField();
        orderMode = pageBaseReq.getOrderMode();
    }

    /**
     * 设置排序
     *
     * @param orderItem
     * @date 2023/1/31 10:39
     * @author zhehen.lu
     */
    public void setOrderItem(OrderItem orderItem) {
        orderList.add(orderItem);
    }

    /**
     * 设置排序
     *
     * @param orderField
     * @param isAsc
     * @date 2023/1/31 10:39
     * @author zhehen.lu
     */
    public void setOrderItem(String orderField, Boolean isAsc) {
        OrderItem orderItem = new OrderItem();
        orderItem.setAsc(isAsc);
        orderItem.setColumn(orderField);
        orderList.add(orderItem);
    }

    /**
     * 是否传入pageBaseReq 有排序字段
     *
     * @param
     * @return {@link boolean}
     * @date 2023/1/31 13:39
     * @author zhehen.lu
     */
    private boolean isPageBaseReqHaveOrder() {
        return entity != null && !StringUtils.isEmpty(orderField) && !StringUtils.isEmpty(orderMode);
    }

    /**
     * 是否存在订单字段
     *
     * @param
     * @return {@link boolean}
     * @date 2023/1/31 13:43
     * @author zhehen.lu
     */
    private boolean isExistOrderField() {
        return Arrays.stream(entity.getDeclaredFields())
                .anyMatch(field -> field.getName().equals(orderField));
    }

    /**
     * 设置订单字段item
     *
     * @param
     * @date 2023/1/31 13:44
     * @author zhehen.lu
     */
    private void setOrderFieldItem() {
        setOrderItem(orderField, "asc".equalsIgnoreCase(orderMode));
    }

    /**
     * 类型枚举
     *
     * @author zhehen.lu
     * @date 2023/1/31 13:07
     */
    public enum TypeEnum {
        // 0以pageBaseReqVO参数传入优先（没传入排序字段时）  1 默认用户调用OrderItem优先 2 pageBaseReqVO优先没传默认以主键排倒叙
        // 3pageBaseReqVo优先， 没传默认更新时间排倒叙
        PARAM_PRIORITY,
        ORDER_ITEM_PRIORITY,
        PARAM_PRIORITY_DEFAULT_PRIMARY,
        PARAM_PRIORITY_DEFAULT_UPDATE_TIME,
        PARAM_PRIORITY_DEFAULT_CREATE_TIME;
    }
}
