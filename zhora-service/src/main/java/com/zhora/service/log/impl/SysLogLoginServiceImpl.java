package com.zhora.service.log.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhora.common.domain.page.PageData;
import com.zhora.common.utils.ConvertUtils;
import com.zhora.constant.Constant;
import com.zhora.dto.log.SysLogLoginDTO;
import com.zhora.entity.log.SysLogLoginEntity;
import com.zhora.mapper.log.SysLogLoginDao;
import com.zhora.service.log.ISysLogLoginService;
import com.zhora.service.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 登录日志
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
@Service
public class SysLogLoginServiceImpl extends BaseServiceImpl<SysLogLoginDao, SysLogLoginEntity> implements ISysLogLoginService {

    @Override
    public PageData<SysLogLoginDTO> page(Map<String, Object> params) {
        IPage<SysLogLoginEntity> page = baseDao.selectPage(
            getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );

        return getPageData(page, SysLogLoginDTO.class);
    }

    @Override
    public List<SysLogLoginDTO> list(Map<String, Object> params) {
        List<SysLogLoginEntity> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysLogLoginDTO.class);
    }

    private QueryWrapper<SysLogLoginEntity> getWrapper(Map<String, Object> params){
        String status = (String) params.get("status");
        String creatorName = (String) params.get("creatorName");

        QueryWrapper<SysLogLoginEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(status), "status", status);
        wrapper.like(StrUtil.isNotBlank(creatorName), "creator_name", creatorName);

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysLogLoginEntity entity) {
        insert(entity);
    }

}