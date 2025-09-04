package com.zhora.service.system;

import com.zhora.common.domain.page.PageData;
import com.zhora.dto.system.SysParamsDTO;
import com.zhora.entity.system.SysParamsEntity;
import com.zhora.service.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 参数管理
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
public interface ISysParamsService extends BaseService<SysParamsEntity> {

    PageData<SysParamsDTO> page(Map<String, Object> params);

    List<SysParamsDTO> list(Map<String, Object> params);

    SysParamsDTO get(Long id);

    void save(SysParamsDTO dto);

    void update(SysParamsDTO dto);

    void delete(Long[] ids);

    /**
     * 根据参数编码，获取参数的value值
     *
     * @param paramCode  参数编码
     */
    String getValue(String paramCode);

    /**
     * 根据参数编码，获取value的Object对象
     * @param paramCode  参数编码
     * @param clazz  Object对象
     */
    <T> T getValueObject(String paramCode, Class<T> clazz);

    /**
     * 根据参数编码，更新value
     * @param paramCode  参数编码
     * @param paramValue  参数值
     */
    int updateValueByCode(String paramCode, String paramValue);
}
