package com.zhora.service.system;

import com.zhora.common.domain.page.PageData;
import com.zhora.dto.system.SysDictTypeDTO;
import com.zhora.entity.system.DictType;
import com.zhora.entity.system.SysDictTypeEntity;
import com.zhora.service.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author zhehen.lu
 */
public interface ISysDictTypeService extends BaseService<SysDictTypeEntity> {

    PageData<SysDictTypeDTO> page(Map<String, Object> params);

    SysDictTypeDTO get(Long id);

    void save(SysDictTypeDTO dto);

    void update(SysDictTypeDTO dto);

    void delete(Long[] ids);

    /**
     * 获取所有字典
     */
    List<DictType> getAllList();

}