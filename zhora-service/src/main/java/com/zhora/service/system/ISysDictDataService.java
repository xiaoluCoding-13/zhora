package com.zhora.service.system;

import com.zhora.common.domain.page.PageData;
import com.zhora.dto.system.SysDictDataDTO;
import com.zhora.entity.system.SysDictDataEntity;
import com.zhora.service.service.BaseService;

import java.util.Map;

/**
 * 数据字典
 *
 * @author zhehen.lu
 */
public interface ISysDictDataService extends BaseService<SysDictDataEntity> {

    PageData<SysDictDataDTO> page(Map<String, Object> params);

    SysDictDataDTO get(Long id);

    void save(SysDictDataDTO dto);

    void update(SysDictDataDTO dto);

    void delete(Long[] ids);

}