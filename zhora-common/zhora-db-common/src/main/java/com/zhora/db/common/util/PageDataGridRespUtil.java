package com.ykyy.ec.db.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ykyy.ec.common.page.PageDataGridRespDTO;
import com.ykyy.ec.common.util.ConvertUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname PageDataGridRespUtil
 * @Description 分页数据处理工具 只针对IPage类做处理
 * @date 2022/7/4 11:22
 * @Created by sherlock
 */
public class PageDataGridRespUtil {
    /**
     * 获取页面返回表格数据
     * @param iPage mybatis-plus分页数据
     * @param sClass 需要转换的class
     * @return {@link PageDataGridRespDTO<T>}
     * @date 2023/2/10 16:05
     * @author sherlock
     */
    public static <T> PageDataGridRespDTO<T> convert(IPage iPage, Class<T> sClass) {
        if (iPage == null) {
            return new PageDataGridRespDTO<>();
        }

        return CollectionUtils.isEmpty(iPage.getRecords()) ?
                convert(iPage, new ArrayList<>()) :
                new PageDataGridRespDTO<T>(iPage.getTotal(), iPage.getCurrent(),
                        iPage.getSize(), ConvertUtil.convertList(iPage.getRecords(), sClass));
    }

    /**
     * 获取页面返回表格数据
     * @param iPage mybatis-plus分页数据
     * @return {@link PageDataGridRespDTO<T>}
     * @date 2023/2/10 16:06
     * @author sherlock
     */
    public static<T> PageDataGridRespDTO<T> convert(IPage<T> iPage) {
        if (iPage == null) {
            return new PageDataGridRespDTO();
        }

        return new PageDataGridRespDTO<T>(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), iPage.getRecords());
    }

    /**
     * 获取页面返回表格数据
     * @param iPage mybatis-plus分页数据
     * @param data 分页的list需要替换的数据
     * @return {@link PageDataGridRespDTO<T>}
     * @date 2023/2/10 16:06
     * @author sherlock
     */
    public static<T> PageDataGridRespDTO<T> convert(IPage iPage, List<T> data) {
        return new PageDataGridRespDTO<T>(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), data);
    }
}
