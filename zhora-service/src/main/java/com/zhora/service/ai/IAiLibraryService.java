package com.zhora.service.ai;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhora.common.domain.page.PageDataGridRespDTO;
import com.zhora.dto.ai.AiLibraryDTO;
import com.zhora.dto.ai.search.AiLibrarySearchDTO;
import com.zhora.entity.ai.AiLibraryEntity;

import java.util.List;

/**
 * 知识库(ai_library)表服务接口
 *
 * @author zhehen.lu
 * @since 2025-09-10 15:24:40
 */
public interface IAiLibraryService extends IService<AiLibraryEntity> {

    PageDataGridRespDTO<AiLibraryDTO> listPage(AiLibrarySearchDTO searchDTO);

    void create(AiLibraryDTO dto);

    AiLibraryDTO getDetailById(Long id);

    AiLibraryDTO getDetail(AiLibrarySearchDTO searchDTO);

    void updateById(AiLibraryDTO dto);

    List<AiLibraryDTO> list(AiLibrarySearchDTO searchDTO);
}
