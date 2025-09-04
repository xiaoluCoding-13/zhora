package com.zhora.service.log.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhora.common.domain.page.PageData;
import com.zhora.common.utils.ConvertUtils;
import com.zhora.constant.Constant;
import com.zhora.dto.log.SysLogOperationDTO;
import com.zhora.entity.log.SysLogOperationEntity;
import com.zhora.mapper.log.SysLogOperationDao;
import com.zhora.service.log.ISysLogOperationService;
import com.zhora.service.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 操作日志
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
@Service
public class SysLogOperationServiceImpl extends BaseServiceImpl<SysLogOperationDao, SysLogOperationEntity> implements ISysLogOperationService {

    @Override
    public PageData<SysLogOperationDTO> page(Map<String, Object> params) {
        IPage<SysLogOperationEntity> page = baseDao.selectPage(
            getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );

        return getPageData(page, SysLogOperationDTO.class);
    }

    @Override
    public List<SysLogOperationDTO> list(Map<String, Object> params) {
        List<SysLogOperationEntity> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysLogOperationDTO.class);
    }

    private QueryWrapper<SysLogOperationEntity> getWrapper(Map<String, Object> params){
        String status = (String) params.get("status");

        QueryWrapper<SysLogOperationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(status), "status", status);

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysLogOperationEntity entity) {
        insert(entity);
    }

}