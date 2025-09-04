package com.zhora.service.log;

import com.zhora.common.domain.page.PageData;
import com.zhora.dto.log.SysLogOperationDTO;
import com.zhora.entity.log.SysLogOperationEntity;
import com.zhora.service.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 操作日志
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
public interface ISysLogOperationService extends BaseService<SysLogOperationEntity> {

    PageData<SysLogOperationDTO> page(Map<String, Object> params);

    List<SysLogOperationDTO> list(Map<String, Object> params);

    void save(SysLogOperationEntity entity);
}