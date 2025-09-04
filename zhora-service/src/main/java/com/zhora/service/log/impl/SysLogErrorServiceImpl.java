package com.zhora.service.log.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhora.common.domain.page.PageData;
import com.zhora.common.utils.ConvertUtils;
import com.zhora.constant.Constant;
import com.zhora.dto.log.SysLogErrorDTO;
import com.zhora.entity.log.SysLogErrorEntity;
import com.zhora.mapper.log.SysLogErrorDao;
import com.zhora.service.log.ISysLogErrorService;
import com.zhora.service.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 异常日志
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
@Service
public class SysLogErrorServiceImpl extends BaseServiceImpl<SysLogErrorDao, SysLogErrorEntity> implements ISysLogErrorService {

    @Override
    public PageData<SysLogErrorDTO> page(Map<String, Object> params) {
        IPage<SysLogErrorEntity> page = baseDao.selectPage(
            getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );

        return getPageData(page, SysLogErrorDTO.class);
    }

    @Override
    public List<SysLogErrorDTO> list(Map<String, Object> params) {
        List<SysLogErrorEntity> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysLogErrorDTO.class);
    }

    private QueryWrapper<SysLogErrorEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<SysLogErrorEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysLogErrorEntity entity) {
        insert(entity);
    }

}