package com.zhora.service.log;

import com.zhora.common.domain.page.PageData;
import com.zhora.dto.log.SysLogErrorDTO;
import com.zhora.entity.log.SysLogErrorEntity;
import com.zhora.service.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 异常日志
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
public interface ISysLogErrorService extends BaseService<SysLogErrorEntity> {

    PageData<SysLogErrorDTO> page(Map<String, Object> params);

    List<SysLogErrorDTO> list(Map<String, Object> params);

    void save(SysLogErrorEntity entity);

}