package com.zhora.mapper.system;

import com.zhora.entity.system.DictData;
import com.zhora.entity.system.SysDictDataEntity;
import com.zhora.mapper.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典数据
 *
 * @author zhehen.lu
 */
@Mapper
public interface SysDictDataMapper extends BaseDao<SysDictDataEntity> {

    /**
     * 字典数据列表
     */
    List<DictData> getDictDataList();
}
