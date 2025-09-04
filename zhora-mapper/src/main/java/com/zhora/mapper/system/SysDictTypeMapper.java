package com.zhora.mapper.system;

import com.zhora.entity.system.DictType;
import com.zhora.entity.system.SysDictTypeEntity;
import com.zhora.mapper.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典类型
 *
 * @author zhehen.lu
 */
@Mapper
public interface SysDictTypeMapper extends BaseDao<SysDictTypeEntity> {

    /**
     * 字典类型列表
     */
    List<DictType> getDictTypeList();

}
