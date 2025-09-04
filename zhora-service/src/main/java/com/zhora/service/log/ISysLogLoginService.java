package com.zhora.service.log;

import com.zhora.common.domain.page.PageData;
import com.zhora.dto.log.SysLogLoginDTO;
import com.zhora.entity.log.SysLogLoginEntity;
import com.zhora.service.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 登录日志
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
public interface ISysLogLoginService extends BaseService<SysLogLoginEntity> {

    PageData<SysLogLoginDTO> page(Map<String, Object> params);

    List<SysLogLoginDTO> list(Map<String, Object> params);

    void save(SysLogLoginEntity entity);
}