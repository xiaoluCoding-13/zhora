package com.zhora.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import com.zhora.dto.system.SysDeptDTO;
import com.zhora.dto.system.search.SysDeptSearchDTO;
import com.zhora.entity.system.SysDeptEntity;

import java.util.List;

/**
 * 部门表(sys_dept)表服务接口
 *
 * @author zhehen.lu
 * @since 2025-08-26 17:46:32
 */
public interface ISysDeptService extends IService<SysDeptEntity> {

    PageDataGridRespDTO<SysDeptDTO> listPage(SysDeptSearchDTO searchDTO);

    void create(SysDeptDTO dto);

    SysDeptDTO getDetailById(Long id);

    SysDeptDTO getDetail(SysDeptSearchDTO searchDTO);

    void updateById(SysDeptDTO dto);

    List<SysDeptDTO> list(SysDeptSearchDTO searchDTO);
}
