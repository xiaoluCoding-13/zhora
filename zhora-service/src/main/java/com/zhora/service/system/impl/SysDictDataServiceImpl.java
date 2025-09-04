package com.zhora.service.system.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhora.common.domain.page.PageData;
import com.zhora.common.utils.ConvertUtils;
import com.zhora.dto.system.SysDictDataDTO;
import com.zhora.entity.system.SysDictDataEntity;
import com.zhora.mapper.system.SysDictDataMapper;
import com.zhora.service.service.impl.BaseServiceImpl;
import com.zhora.service.system.ISysDictDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

/**
 * 字典类型
 *
 * @author zhehen.lu
 */
@Service
public class SysDictDataServiceImpl extends BaseServiceImpl<SysDictDataMapper, SysDictDataEntity> implements ISysDictDataService {

    @Override
    public PageData<SysDictDataDTO> page(Map<String, Object> params) {
        IPage<SysDictDataEntity> page = baseDao.selectPage(
                getPage(params, "sort", true),
                getWrapper(params)
        );

        return getPageData(page, SysDictDataDTO.class);
    }

    private QueryWrapper<SysDictDataEntity> getWrapper(Map<String, Object> params){
        Long dictTypeId = Long.parseLong((String) params.get("dictTypeId"));
        String dictLabel = (String) params.get("dictLabel");
        String dictValue = (String) params.get("dictValue");

        QueryWrapper<SysDictDataEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_type_id", dictTypeId);
        wrapper.like(StrUtil.isNotBlank(dictLabel), "dict_label", dictLabel);
        wrapper.like(StrUtil.isNotBlank(dictValue), "dict_value", dictValue);

        return wrapper;
    }

    @Override
    public SysDictDataDTO get(Long id) {
        SysDictDataEntity entity = baseDao.selectById(id);

        return ConvertUtils.sourceToTarget(entity, SysDictDataDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictDataDTO dto) {
        SysDictDataEntity entity = ConvertUtils.sourceToTarget(dto, SysDictDataEntity.class);

        insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictDataDTO dto) {
        SysDictDataEntity entity = ConvertUtils.sourceToTarget(dto, SysDictDataEntity.class);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        //删除
        deleteBatchIds(Arrays.asList(ids));
    }

}