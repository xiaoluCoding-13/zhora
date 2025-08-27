package com.zhora.common.utils;

import cn.hutool.core.bean.BeanUtil;
import com.zhora.common.dto.page.PageDataGridRespDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname PageDataGridCommonRespUtil
 * @Description 分页工具类 公共的， 不针对
 * @Date 2025/2/27 19:47
 * @Created by zhehen.lu
 */
public class PageDataGridCommonRespUtil {
    /**
     * 转换页面返回表格数据
     * @param respVO 分页数据
     * @param sClass 需要转换的class
     * @return {@link PageDataGridRespDTO <T>}
     * @date 2025/2/27 16:07
     * @author zhehen.lu
     */
    public static <T> PageDataGridRespDTO<T> convert(PageDataGridRespDTO respVO, Class<T> sClass) {
        return new PageDataGridRespDTO<T>(respVO.getTotal(), respVO.getCurrent(),
                respVO.getPageSize(), BeanUtil.copyToList(respVO.getList(), sClass));
    }

    /**
     * 转换页面返回表格数据
     * @param respVO 分页数据
     * @param data 分页的list需要替换的数据
     * @return {@link PageDataGridRespDTO<T>}
     * @date 2025/2/27 16:08
     * @author zhehen.lu
     */
    public static <T> PageDataGridRespDTO<T> convert(PageDataGridRespDTO respVO, List<T> data) {
        return new PageDataGridRespDTO<T>(respVO.getTotal(), respVO.getCurrent(),
                respVO.getPageSize(), data);
    }

    /**
     * 获取空数据
     * @param
     * @return {@link PageDataGridRespDTO}
     * @date 2025/2/27 16:08
     * @author zhehen.lu
     */
    public static PageDataGridRespDTO emptyPage(){
        return new PageDataGridRespDTO(0L,1L,10L,new ArrayList());
    }
}
