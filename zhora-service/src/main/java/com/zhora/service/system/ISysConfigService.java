package com.zhora.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import com.zhora.dto.system.SysConfigDTO;
import com.zhora.dto.system.search.SysConfigSearchDTO;
import com.zhora.entity.system.SysConfigEntity;

import java.util.List;

/**
 * 参数配置表(sys_config)表服务接口
 *
 * @author zhehen.lu
 * @since 2025-08-28 15:16:38
 */
public interface ISysConfigService extends IService<SysConfigEntity> {

    PageDataGridRespDTO<SysConfigDTO> listPage(SysConfigSearchDTO searchDTO);

    Long create(SysConfigDTO dto);

    SysConfigDTO getDetailById(Long id);

    SysConfigDTO getDetail(SysConfigSearchDTO searchDTO);

    Long updateById(SysConfigDTO dto);

    List<SysConfigDTO> list(SysConfigSearchDTO searchDTO);
}
